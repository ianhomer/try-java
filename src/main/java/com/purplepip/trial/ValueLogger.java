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

package com.purplepip.trial;

import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValueLogger {
  private final Logger log;
  private final String category;
  private final int depth;
  private final String prefix;

  public ValueLogger(Class<?> clazz) {
    this(clazz.getName(), 0);
  }

  /**
   * Create a value logger.
   *
   * @param category category of logger
   * @param depth depth of logger
   */
  public ValueLogger(String category, int depth) {
    this.category = category;
    log = LoggerFactory.getLogger(category);
    this.depth = depth;
    this.prefix = new String(new char[depth]).replace("\0", " .");
  }

  public ValueLogger child(String category) {
    return new ValueLogger(this.category + "." + category, depth + 1);
  }

  /**
   * Output info.
   *
   * @param value value to output
   */
  public void info(Object value) {
    if (value instanceof Stream) {
      info((Stream) value);
    } else if (value instanceof Named) {
      info((Named) value);
    } else {
      log.info("{} : {} ", prefix, value);
    }
  }

  public void info(Named value) {
    log.info("{} : {} ", prefix, value.name());
  }

  public void info(Stream<?> stream) {
    log.info("{} : {} ", prefix, stream.toArray());
  }
}
