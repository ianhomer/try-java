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

package com.purplepip.java10;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TryVar {
  public Optional<String> tryVar() {
    var values = List.of("x", "y", "z");
    var x = Optional.of("test");
    var map = new HashMap<String, List<String>>();
    map.put(x.get(), values);
    return x;
  }
}
