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

package com.purplepip.music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@ToString(of = "name")
@EqualsAndHashCode(of = "name")
public class Song implements Comparable<Song> {
  private AtomicInteger playCount = new AtomicInteger();

  /**
   * A copy constructor.
   *
   * @param song song to copy
   */
  public Song(Song song) {
    name(song.name());
    length(song.length());
    genres(song.genres.toArray(new Genre[] {}));
  }

  public Song() {
  }

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private long length;

  @Getter
  private Genre primaryGenre;

  private Set<Genre> genres = new HashSet<>();

  private List<String> labels = new ArrayList<>();

  @Override
  public int compareTo(Song o) {
    return name.compareTo(o.name);
  }

  public Song labels(String... labels) {
    this.labels.addAll(Arrays.asList(labels));
    return this;
  }

  public Stream<String> labels() {
    return labels.stream();
  }

  /**
   * Set genres.
   *
   * @param genres genres to set
   * @return this song
   */
  public Song genres(Genre... genres) {
    if (primaryGenre == null && genres.length > 0) {
      primaryGenre = genres[0];
    }
    this.genres.addAll(Arrays.asList(genres));
    return this;
  }

  public Stream<Genre> genres() {
    return genres.stream();
  }

  public Song copy() {
    return new Song(this);
  }

  public void play() {
    playCount.incrementAndGet();
  }

  public int getPlayCount() {
    return playCount.get();
  }
}
