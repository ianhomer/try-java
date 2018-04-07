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
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Sequence {
  HashSet<Note> notes = new HashSet<>();
  private AtomicInteger startCount = new AtomicInteger();

  public Sequence(Set<Note> notes) {
    this.notes.addAll(notes);
  }

  public Stream<Note> notes() {
    return notes.stream();
  }

  public void start() {
    startCount.incrementAndGet();
  }

  public int getStartCount() {
    return startCount.get();
  }
}
