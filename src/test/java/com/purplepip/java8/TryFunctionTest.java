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

import static com.purplepip.trial.TrialAssert.assertExecution;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TryFunctionTest {
  @Test
  public void testWithTrial() {
    assertExecution(TryFunction.class);
  }

  @Test
  public void tryFunction() {
    assertEquals("faster, faster : faster, faster", new TryFunction().tryFunction());
  }

  @Test
  public void tryInlineLambda() {
    assertEquals("faster, faster : faster, faster", new TryFunction().tryInlineLambda());
  }

  @Test
  public void tryNonFunctionalAlternativeToInlineLambda() {
    assertEquals("faster, faster : faster, faster", new TryFunction()
        .tryNonFunctionalAlternativeToInlineLambda());
  }
}