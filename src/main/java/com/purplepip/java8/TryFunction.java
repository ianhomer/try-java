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

import static com.purplepip.java8.Genre.INDIE;
import static com.purplepip.java8.Genre.PUNK;

import java.util.function.Function;

public class TryFunction {
  private Song song = new Song().name("my-song").genres(PUNK, INDIE);

  /**
   * Inline lambda usage.
   *
   * @return result
   */
  public String tryFunction() {
    Function<Song, String> function = s -> {
      switch (s.primaryGenre()) {
        case PUNK:
          return "faster, faster";
        default:
          return "keep going";
      }
    };
    String value = function.apply(song);
    return value + " : " + value;
  }

  /**
   * Inline lambda usage.
   *
   * @return result
   */
  public String tryInlineLambda() {
    String value = ((Function<Song, String>) s -> {
        switch (s.primaryGenre()) {
          case PUNK:
            return "faster, faster";
          default:
            return "keep going";
        }
      }
    ).apply(song);
    return value + " : " + value;
  }

  /**
   * Compare with non-functional usage.
   *
   * @return result
   */
  public String tryNonFunctionalAlternativeToInlineLambda() {
    /*
     * The disadvantage of the non-functional approach is that there is no enforcement that
     * value is set.   With more complex logic branches this could lead to hidden bugs.  This
     * is because the logic relies on a side effect setting the result.
     */
    String value;
    switch (song.primaryGenre()) {
      case PUNK:
        value = "faster, faster";
        break;
      default:
        value = "keep going";
    }
    return value + " : " + value;
  }
}
