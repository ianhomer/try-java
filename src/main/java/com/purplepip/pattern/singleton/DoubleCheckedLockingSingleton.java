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

package com.purplepip.pattern.singleton;

import java.util.Random;

/**
 * Lazy initialisation, with double checked locked for thread safety and performance optimisation
 * and volatile instance to ensure that a second thread reads instance value from main memory and
 * not a CPU cache.  see http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
 */
public class DoubleCheckedLockingSingleton implements MySingleton {
  private static volatile DoubleCheckedLockingSingleton INSTANCE;
  private long seed;

  private DoubleCheckedLockingSingleton() {
    seed = new Random().nextLong();
  }

  /**
   * Get singleton instance.
   *
   * @return singleton
   */
  public static DoubleCheckedLockingSingleton getInstance() {
    /*
     * First check to prevent unnecessary use of synchronized.
     */
    if (INSTANCE == null) {
      synchronized (DoubleCheckedLockingSingleton.class) {
        /*
         * Second check in case of race condition.
         */
        if (INSTANCE == null) {
          INSTANCE = new DoubleCheckedLockingSingleton();
        }
      }
    }
    return INSTANCE;
  }

  public long getSeed() {
    return seed;
  }
}
