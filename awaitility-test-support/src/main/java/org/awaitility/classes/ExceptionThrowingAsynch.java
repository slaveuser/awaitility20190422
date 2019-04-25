/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.awaitility.classes;

public class ExceptionThrowingAsynch {

    private final Throwable throwable;

    public ExceptionThrowingAsynch(Throwable throwable) {
        this.throwable = throwable;
    }

    public void perform() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (throwable instanceof RuntimeException) {
                    throw (RuntimeException) throwable;
                } else if(throwable instanceof Error) {
                    throw (Error) throwable;
                }
                throw new RuntimeException(throwable);
            }
        });

        thread.start();
    }
}
