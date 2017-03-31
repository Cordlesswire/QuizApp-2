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
 * Last modified 3/30/17 9:29 PM
 */

package com.example.android.quizapp;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class specification for Quiz Question
 */
public class QuizQuestion implements Iterable<QuestAnswer> {
    private String mQuestion;
    private ArrayList<QuestAnswer> mAnswers;

    public QuizQuestion(String question) {
        mQuestion = question;
        mAnswers = new ArrayList<>();
    }

    /**
     * Add answer version for the question
     * @param answer - a version of an answer
     */
    public void addAnswer(QuestAnswer answer) {
        mAnswers.add(answer);
    }

    /**
     * Gets question string
     * @return -
     */
    public String getQuestion() {
        return mQuestion;
    }

    /**
     * Checks for the question to be answered correctly
     * @return
     */
    public boolean isCorrect() {
        boolean result = true;
        for (QuestAnswer qa : mAnswers) {
            result &= qa.isCorrect();
        }
        return result;
    }

    @Override
    public Iterator<QuestAnswer> iterator() {
        return mAnswers.iterator();
    }
}
