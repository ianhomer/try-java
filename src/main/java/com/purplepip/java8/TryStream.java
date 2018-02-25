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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TryStream {
  private List<String> names = Arrays.asList("song1", "song2", "riff1");
  private static final BiConsumer<String, String> LOG_RESULT =
      (label, result) -> LOG.info("{} : {}", label, result);

  /**
   * Try main.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    LOG.info("filter : {}", (Object) new TryStream().tryFilter().toArray());
    LOG.info("map : {}", (Object) new TryStream().tryMap().toArray());
    new TryStream().tryReduce().ifPresent(result -> LOG_RESULT.accept("reduce", result));
  }

  public Stream<String> tryFilter() {
    return names.stream().filter(s -> s.startsWith("song"));
  }

  public Stream<String> tryMap() {
    return names.stream().map(s -> "new-" + s);
  }

  public Optional<String> tryReduce() {
    return names.stream().reduce((x, y) -> "( " + x + " , " + y + " )");
  }
}
