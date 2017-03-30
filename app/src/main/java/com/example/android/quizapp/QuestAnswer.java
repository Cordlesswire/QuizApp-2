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
 * Last modified 3/30/17 9:22 PM
 */

package com.example.android.quizapp;

/**
 * Class defines an answer for a question
 */
public class QuestAnswer<T> {
    private final T mRightAnswer; // Right answer expected
    private final String mGuess; // Guess describing the answer
    private T mUserAnswer;

    /**
     * Creates the answer with Guess and RightAnswer
     * @param rightAnswer -
     * @param guess -
     */
    public QuestAnswer(T rightAnswer, String guess) {
        mRightAnswer = rightAnswer;
        mGuess = guess;
    }

    /**
     * Returns the Guess string
     * @return mGuess
     */
    public String getGuess() {
        return mGuess;
    }

    /**
     * Checks if user's answer is correct
     * @return true if it is correct
     */
    public boolean isCorrect() {
        return mRightAnswer.equals(mUserAnswer);
    }

    /**
     * Sets user answer
     * @param answer -
     */
    public void setUserAnswer(T answer) {
        mUserAnswer = answer;
    }
}
