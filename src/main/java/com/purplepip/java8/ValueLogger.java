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

import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValueLogger {
  private String category;
  private String subCategory;
  private int depth;
  private String label;

  public ValueLogger(String category) {
    this(category, null);
  }

  public ValueLogger(String category, String subCategory) {
    this(category, subCategory, 0);
  }

  public ValueLogger(String category, int depth) {
    this(category, null, depth);
  }

  /**
   * Create a new value logger.
   *
   * @param category category
   * @param subCategory sub-category
   * @param depth depth of logging statement
   */
  public ValueLogger(String category, String subCategory, int depth) {
    this.category = category;
    this.subCategory = subCategory;
    this.depth = depth;

    label = new String(new char[depth]).replace("\0", " ...")
        + category
        + (subCategory == null ? "" : "." + subCategory);
  }

  public void log(Object value) {
    LOG.info("{} : {} ", label, value);
  }

  public void log(String subCategory, Object value) {
    LOG.info("{}.{} : {} ", label, subCategory, value);
  }

  public void log(Stream<?> stream) {
    LOG.info("{} : {} ", label, stream.toArray());
  }
}
