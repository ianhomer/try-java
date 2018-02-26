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

import static org.junit.Assert.assertEquals;

import com.purplepip.trial.Trial;
import org.junit.Test;

public class TrialTest {
  @Test
  public void testTrial() {
    Trial trial = new Trial(TrySomething.class);
    trial.run();
    assertEquals(1, trial.getExecutionCount());
  }

  @Test
  public void testMain() {
    Trial.main(new String[] {"com.purplepip.java8.TrialTest$TrySomething"});
  }

  public static class TrySomething {
    public String trySomething() {
      return "something";
    }
  }
}