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

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

/**
 * Execute all try methods on a class simply to execute the code, demonstrating and validating
 * usage.   This is just a light weight way to explore functionality by triggering the
 * example code.
 */
@Slf4j
public class Trial {
  private final Class<?> clazz;
  private String filter;

  /**
   * Main execution.
   *
   * @param args command line arguments, first argument is class pattern, second argument is
   *             method name pattern.
   */
  public static void main(String[] args) {
    Thread.currentThread().setName("main()");
    if (args.length == 0) {
      throw new IllegalStateException("Trial arguments MUST specify a class name");
    }

    /*
     * Loop over class name and execute each as a trial.
     */
    Stream.of(args[0].split(",")).forEach(className -> {
      try {
        Trial trial = new Trial(Class.forName(className));
        /*
         * Set the filter.
         */
        if (args.length > 1 && args[1] != null) {
          trial.filter(args[1]);
        }
        trial.run();
      } catch (ClassNotFoundException e) {
        LOG.error("Cannot find class " + className, e);
      }
    });
  }

  /**
   * Allow the execution of methods trying something out.
   *
   * @param clazz class providing try methods.
   */
  Trial(Class<?> clazz) {
    this.clazz = clazz;
  }

  private void filter(String filter) {
    this.filter = filter.toLowerCase();
  }

  /**
   * Run trial.
   */
  Execution run() {
    ValueLogger logger = new ValueLogger(clazz);
    final Execution execution = new Execution();
    Arrays.stream(clazz.getMethods())
        // Trial methods start with "try"
        .filter(method -> method.getName().startsWith("try")
          && (filter == null || method.getName().toLowerCase().contains(filter)))
        .forEach(method -> {
          try {
            Object result = method.invoke(method.getDeclaringClass().newInstance());
            logger.child(method.getName()).info(result);
            execution.increment();
          } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            LOG.error("Cannot invoke " + method, e);
          }
        });
    return execution;
  }
}
