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

package com.purplepip.pattern.adapter;

import com.purplepip.music.Note;
import com.purplepip.music.Song;
import java.util.Comparator;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * Adapter from a sequence to a song.
 */
public class SequenceSong extends Song {
  Sequence sequence;

  /**
   * Create a song based on a sequence.
   *
   * @param prototype prototype to base the song on
   * @param sequence sequence of notes
   */
  public SequenceSong(Song prototype, @NotNull Sequence sequence) {
    super(prototype);
    Objects.requireNonNull(sequence, "Sequence must not be null");
    this.sequence = sequence;
    /*
     * Calculate the length of the song as the last note.
     */
    length(sequence.notes.stream().map(Note::time)
        .max(Comparator.naturalOrder()).orElse(-1L));
  }

  @Override
  public void play() {
    sequence.start();
    super.play();
  }
}
