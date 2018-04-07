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

import com.purplepip.music.Song;
import com.purplepip.trial.ValueLogger;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TryStream {
  private List<Song> songs = Arrays.asList(
      new Song().name("song1").length(5).labels("punk", "rock"),
      new Song().name("song1").length(5),
      new Song().name("song2").length(10).labels("rap", "punk"),
      new Song().name("song3").length(7).labels("soul"),
      new Song().name("song22").length(15),
      new Song().name("riff1").length(3),
      new Song().name("riff2").length(4)
  );

  public boolean tryAllMatch() {
    return songs.stream()
        .allMatch(song -> song.name().startsWith("song"));
  }

  public boolean tryAnyMatch() {
    return songs.stream()
        .anyMatch(song -> song.name().startsWith("song"));
  }

  /**
   * Try collect.
   */
  public List<Object> tryCollect() {
    return songs.stream()
        .filter(song -> song.name().startsWith("song"))
        .collect(Collectors.toList());
  }

  /**
   * Try joining collect.
   */
  public String tryCollectJoining() {
    return songs.stream()
        .map(Song::name)
        .collect(Collectors.joining());
  }

  /**
   * Try custom joining collect.
   */
  public String tryCollectJoiningCustom() {
    return songs.stream()
        .map(song -> "  <li>" + song.name() + "</li>")
        .collect(Collectors.joining("\n", "\n<ul>\n", "\n</ul>"));
  }

  public IntSummaryStatistics tryCollectSummarizingDouble() {
    return songs.stream()
        .collect(Collectors.summarizingInt(Song::length));
  }

  public Stream<Song> tryConcat() {
    return Stream.concat(songs.stream(), songs.stream());
  }

  /**
   * Return stream of distinct objects.
   */
  public Stream<Song> tryDistinct() {
    return songs.stream().distinct();
  }

  /**
   * Filter out only songs that start with the word song.
   */
  public Stream<Song> tryFilter() {
    return songs.stream()
        .filter(s -> s.name().startsWith("song"));
  }

  /**
   * Find first.
   */
  public Song tryFindFirst() {
    return songs.stream()
        .findFirst()
        .orElseThrow(IllegalStateException::new);
  }

  /**
   * Find any.
   */
  public Song tryFindAny() {
    return songs.stream()
        .findAny()
        .orElseThrow(IllegalStateException::new);
  }

  /**
   * Find all labels used in all songs.
   */
  public Stream<String> tryFlatMap() {
    return songs.stream()
        .flatMap(Song::labels)
        .distinct();
  }

  /**
   * Generate a stream of 10 random integers.
   */
  public Stream<?> tryGenerate() {
    return Stream
        .generate(new Random()::nextInt)
        .limit(10);
  }

  /**
   * Create a stream of the first 10 numbers in a series.
   */
  public Stream<?> tryIterate() {
    return Stream
        .iterate(2L, n -> n * 2)
        .limit(10);
  }

  /**
   * Get the iterator for a stream.
   */
  public Iterator<Song> tryIterator() {
    return songs.stream()
        .filter(song -> song.name().startsWith("song"))
        .iterator();
  }

  /**
   * Demonstrate that intermediate operations do not get executed until necessary.
   */
  public long tryLazinessWithPeeks() {
    ValueLogger logger = new ValueLogger(TryStream.class).child("laziness");
    return songs.stream()
        .peek(logger.child("peek1")::info)
        .filter(song -> song.name().startsWith("song"))
        .peek(logger.child("peek3")::info)
        .filter(song -> song.name().endsWith("2"))
        .peek(logger.child("peek3")::info)
        .count();
  }

  /**
   * Map songs into stream of songs with new name.
   */
  public Stream<Song> tryMap() {
    return songs.stream()
        .map(song -> new Song(song).name("new-" + song.name()));
  }

  /**
   * Get maximum song length.
   */
  public int tryMax() {
    return songs.stream()
        .mapToInt(Song::length).max()
        .orElse(-1);
  }

  /**
   * Get minimum song length.
   */
  public int tryMin() {
    return songs.stream()
        .mapToInt(Song::length).min()
        .orElse(-1);
  }

  public boolean tryNoneMatch() {
    return songs.stream()
        .noneMatch(s -> s.name().startsWith("poem"));
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

  public Stream<Song> trySkip() {
    return songs.stream()
        .skip(2);
  }

  public Stream<Song> trySorted() {
    return songs.stream()
        .sorted();
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
