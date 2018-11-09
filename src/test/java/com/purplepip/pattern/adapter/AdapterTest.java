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

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.purplepip.music.Note;
import com.purplepip.music.Song;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class AdapterTest {
  @Test
  void testAdapter() {
    Sequence sequence = new Sequence(
        new HashSet<>(asList(
            new Note(60,10),
            new Note(72,20)
        ))
    );
    Song song = new SequenceSong(new Song().name("adapted"), sequence);
    song.play();
    assertEquals(1, song.getPlayCount());
    assertEquals(1, sequence.getStartCount());
    assertEquals(20, song.length());
  }


}