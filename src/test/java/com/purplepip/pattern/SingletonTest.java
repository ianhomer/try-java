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

package com.purplepip.pattern;

import static org.junit.Assert.assertEquals;

import java.util.function.Supplier;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class SingletonTest {
  private Supplier<MySingleton> supplier;

  public SingletonTest(Supplier<MySingleton> supplier) {
    this.supplier = supplier;
  }

  /**
   * Get parameters for tests.
   *
   * @return parameters
   */
  @Parameterized.Parameters
  public static Iterable<Supplier<MySingleton>> parameters() {
    return Lists.newArrayList(
        DoubleCheckedLockingSingleton::getInstance,
        () -> SingletonEnum.INSTANCE
    );
  }

  @Test
  public void testSingleton() {
    MySingleton singleton = supplier.get();
    assertEquals(supplier.get().getSeed(), singleton.getSeed());
  }
}