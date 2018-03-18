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

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TryOptional {
  public void tryCaseReturn() {
    LOG.debug(getMessage(new Song().name("my-song").genres(PUNK, INDIE)));
    LOG.debug(getMessage(null));
  }

  /**
   * Try a return pattern from within Optional.
   *
   * @return result of case statement
   */
  public String getMessage(Song song) {
    return Optional.ofNullable(song).map(this::getMessageFromSong).orElse("why no song?");
  }

  /**
   * Get message without using this optional construct, to compare the code.
   *
   * @param song song
   * @return message
   */
  public String getMessageWithoutOptional(Song song) {
    return song == null ? "why no song?" : getMessageFromSong(song);
  }

  private String getMessageFromSong(Song song) {
    switch (song.primaryGenre()) {
      case PUNK:
        return "faster, faster";
      default:
        return "keep going";
    }
  }
}
