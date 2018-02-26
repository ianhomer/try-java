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

import com.purplepip.trial.ValueLogger;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TryStream {
  private List<Song> songs = Arrays.asList(
      new Song().name("song1").length(5),
      new Song().name("song2").length(10),
      new Song().name("song22").length(15),
      new Song().name("riff1").length(3),
      new Song().name("riff2").length(4)
  );

  public boolean tryAllMatch() {
    return songs.stream()
        .allMatch(s -> s.name().startsWith("song"));
  }

  public boolean tryAnyMatch() {
    return songs.stream()
        .anyMatch(s -> s.name().startsWith("song"));
  }

  public List<Object> tryCollect() {
    return songs.stream()
        .filter(s -> s.name().startsWith("song"))
        .collect(Collectors.toList());
  }

  public String tryCollectJoining() {
    return songs.stream()
        .map(Song::name)
        .collect(Collectors.joining());
  }

  public String tryCollectJoiningCustom() {
    return songs.stream()
        .map(s -> "  <li>" + s.name() + "</li>")
        .collect(Collectors.joining("\n", "\n<ul>\n", "\n</ul>"));
  }

  public IntSummaryStatistics tryCollectSummarizingDouble() {
    return songs.stream()
        .collect(Collectors.summarizingInt(Song::length));
  }

  /**
   * Filter out only songs that start with the word song.
   */
  public Stream<Song> tryFilter() {
    return songs.stream()
        .filter(s -> s.name().startsWith("song"));
  }

  /**
   * Stream iterator.
   */
  public Iterator<Song> tryIterator() {
    return songs.stream()
        .filter(s -> s.name().startsWith("song"))
        .iterator();
  }

  /**
   * Demonstrate that intermediate operations do not get executed until necessary.
   */
  public long tryLaziness() {
    ValueLogger logger = new ValueLogger(TryStream.class).child("laziness");
    return songs.stream()
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
    return songs.stream()
        .map(s -> s.copy().name("new-" + s.name()));
  }

  /**
   * Reduce stream of songs into one song.
   */
  public Song tryReduce() {
    return songs.stream()
        .reduce((x, y) ->
            new Song().name(x.name() + ":" + y.name()).length(x.length() + y.length())
        ).orElseThrow(IllegalStateException::new);
  }

  /**
   * Sum up all the lengths.
   */
  public int trySum() {
    return songs.stream()
        .mapToInt(Song::length)
        .sum();
  }
}
