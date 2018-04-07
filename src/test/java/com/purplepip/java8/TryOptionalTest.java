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

import static com.purplepip.music.Genre.PUNK;
import static org.junit.Assert.assertEquals;

import com.purplepip.music.Song;
import com.purplepip.trial.Execution;
import com.purplepip.trial.Trial;
import org.junit.Assert;
import org.junit.Test;

public class TryOptionalTest {
  @Test
  public void testWithTrial() {
    Execution execution = new Trial(TryOptional.class).run();
    Assert.assertTrue(execution.getCount() > 0);
  }

  @Test
  public void testCaseReturn() {
    assertEquals("faster, faster", new TryOptional().getMessage(new Song().genres(PUNK)));
    assertEquals("why no song?", new TryOptional().getMessage(null));
    assertEquals("faster, faster", new TryOptional()
        .getMessageWithoutOptional(new Song().genres(PUNK)));
    assertEquals("why no song?", new TryOptional()
        .getMessageWithoutOptional(null));
  }
}