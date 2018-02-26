/*
 * Copyright (c) 2017 the original author or authors. All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.purplepip.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TryStream {
  private List<Song> names = Arrays.asList(
      new Song().name("song1").length(5),
      new Song().name("song2").length(10),
      new Song().name("song22").length(15),
      new Song().name("riff1").length(3),
      new Song().name("riff2").length(4)
  );
  private static ValueLogger LOGGER = new ValueLogger(TryStream.class);

  /**
   * Try main.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    Thread.currentThread().setName("main()");
    LOGGER.child("filter").info(new TryStream().tryFilter());
    LOGGER.child("map").info(new TryStream().tryMap());
    LOGGER.child("sum").info(new TryStream().trySum());
    LOGGER.child("laziness").info(new TryStream().tryLaziness());
    LOGGER.child("reduce").info(new TryStream().tryReduce().orElse(null));
  }

  /**
   * Filter out only songs that start with the word song.
   */
  public Stream<Song> tryFilter() {
    return names.stream().filter(s -> s.name().startsWith("song"));
  }

  /**
   * Demonstrate that intermediate operations do not get executed until necessary.
   */
  public long tryLaziness() {
    ValueLogger logger = LOGGER.child("laziness");
    return names.stream()
        .peek(logger.child("peek1")::info)
        .filter(s -> {
          logger.child("filter1").info(s.name());
          return s.name().startsWith("song");
        })
        .peek(logger.child("peek3")::info)
        .filter(s -> {
          logger.child("filter2").info(s.name());
          return s.name().endsWith("2");
        })
        .peek(logger.child("peek3")::info)
        .count();
  }

  /**
   * Map songs into stream of songs with new name.
   */
  public Stream<Song> tryMap() {
    return names.stream().map(s -> s.copy().name("new-" + s.name()));
  }

  /**
   * Reduce stream of songs into one song.
   */
  public Optional<Song> tryReduce() {
    return names.stream().reduce((x, y) ->
        new Song().name(x.name() + ":" + y.name()).length(x.length() + y.length())
    );
  }

  /**
   * Sum up all the lengths.
   */
  public int trySum() {
    return names.stream().mapToInt(Song::length).sum();
  }
}
