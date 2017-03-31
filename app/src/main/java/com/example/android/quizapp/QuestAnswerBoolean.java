/*
 * Copyright (c) 2017. Ruslan Primak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Last modified 3/30/17 11:55 PM
 */

package com.example.android.quizapp;

public class QuestAnswerBoolean extends QuestAnswer<Boolean> {
    /**
     * Creates the answer with Guess and RightAnswer
     *
     * @param rightAnswer -
     * @param guess       -
     */
    public QuestAnswerBoolean(Boolean rightAnswer, String guess) {
        super(rightAnswer, guess);
    }
}
