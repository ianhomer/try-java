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
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TryStream {
  private List<Song> names = Arrays.asList(
      new Song().name("song1").length(5),
      new Song().name("song2").length(10),
      new Song().name("song22").length(15),
      new Song().name("riff1").length(3)
  );

  private static final BiConsumer<String, String> LOG_RESULT =
      (label, result) -> LOG.info("{} : {}", label, result);

  /**
   * Try main.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    new ValueLogger("filter").log(new TryStream().tryFilter());
    new ValueLogger("map").log(new TryStream().tryMap());
    new ValueLogger("sum").log(new TryStream().trySum());
    new ValueLogger("laziness").log(new TryStream().tryLaziness());
    new TryStream().tryReduce()
        .ifPresent(result -> LOG_RESULT.accept("reduce", result.toString()));
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
    ValueLogger logger = new ValueLogger("laziness", 1);
    return names.stream()
        .peek(s -> logger.log("peek1", s.name()))
        .filter(s -> {
          logger.log("filter1", s.name());
          return s.name().startsWith("song");
        })
        .peek(s -> logger.log("peek2", s.name()))
        .filter(s -> {
          logger.log("filter2", s.name());
          return s.name().endsWith("2");
        })
        .peek(s -> logger.log("peek3", s.name()))
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

  @Accessors(fluent = true)
  @ToString
  public static class Song {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int length;

    public Song copy() {
      return new Song().name(name).length(length);
    }
  }
}
