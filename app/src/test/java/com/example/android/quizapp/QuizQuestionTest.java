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
 * Last modified 3/30/17 9:46 PM
 */

package com.example.android.quizapp;

import com.google.gson.Gson;

import org.junit.Test;
import org.junit.experimental.theories.internal.BooleanSupplier;

import static org.junit.Assert.*;

public class QuizQuestionTest {
    @Test
    public void isCorrect() throws Exception {
        QuizQuestion question = new QuizQuestion("Select options question");

        QuestAnswer<Boolean> ansBoolean = new QuestAnswer<>(true, "It is right option");
        ansBoolean.setUserAnswer(true);
        question.addAnswer(ansBoolean);

        ansBoolean = new QuestAnswer<>(false, "It is wrong option");
        ansBoolean.setUserAnswer(false);
        question.addAnswer(ansBoolean);

        assertEquals(true, question.isCorrect());

        ansBoolean = new QuestAnswer<>(true, "Another right option");
        ansBoolean.setUserAnswer(false);
        question.addAnswer(ansBoolean);

        assertEquals(false, question.isCorrect());

        QuizQuestion inputQuest = new QuizQuestion("Specify correct value");

        QuestAnswer<String> ansString = new QuestAnswer<>("ABCDEF", "void");
        ansString.setUserAnswer("ABCDEF");
        inputQuest.addAnswer(ansString);
        assertEquals(true, inputQuest.isCorrect());

        ansString.setUserAnswer("ABCDEFG");
        assertEquals(false, inputQuest.isCorrect());
    }

    @Test
    public void gsonTest() throws Exception {
        QuizQuestion question = new QuizQuestion("Select options question");

        QuestAnswer<Boolean> ansBoolean = new QuestAnswer<>(true, "It is right option");
        ansBoolean.setUserAnswer(true);
        question.addAnswer(ansBoolean);

        ansBoolean = new QuestAnswer<>(false, "It is wrong option");
        ansBoolean.setUserAnswer(false);
        question.addAnswer(ansBoolean);

        Gson gson = new Gson();
        QuizQuestion questGson = gson.fromJson(gson.toJson(question), QuizQuestion.class);

        assertEquals(true, questGson.isCorrect());

        ansBoolean = new QuestAnswer<>(true, "Another right option");
        ansBoolean.setUserAnswer(false);
        question.addAnswer(ansBoolean);

        questGson = gson.fromJson(gson.toJson(question), QuizQuestion.class);
        assertEquals(false, questGson.isCorrect());
    }
}