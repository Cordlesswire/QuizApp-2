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
 * Last modified 3/30/17 9:34 PM
 */

package com.example.android.quizapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;

import static org.junit.Assert.assertEquals;

public class QuestAnswerTest {
    @Test
    public void isCorrect() throws Exception {
        // String answer
        String rightString;
        String wrongString;
        QuestAnswer<String> answerString;

        rightString = "1234556789";
        wrongString = "123455678";
        answerString = new QuestAnswer<>(rightString, rightString);

        answerString.setUserAnswer(rightString);
        assertEquals(true, answerString.isCorrect());

        answerString.setUserAnswer(wrongString.concat("9"));
        assertEquals(true, answerString.isCorrect());

        // Boolean answer
        QuestAnswer<Boolean> answerBool = new QuestAnswer<>(true, "It is correct answer");

        answerBool.setUserAnswer(true);
        assertEquals(true, answerBool.isCorrect());

        answerBool.setUserAnswer(false);
        assertEquals(false, answerBool.isCorrect());
    }

    @Test
    public void gsonTest() throws Exception {
        Gson gson = new Gson();

        // String answer
        String rightString;
        String wrongString;
        QuestAnswer<String> answerString;

        rightString = "1234556789";
        wrongString = "123455678";
        Type genericType = new TypeToken<QuestAnswer<String>>(){}.getType();
        answerString = gson.fromJson(gson.toJson(new QuestAnswer<>(rightString, rightString)),
                genericType);

        answerString.setUserAnswer(rightString);
        assertEquals(true, answerString.isCorrect());

        answerString.setUserAnswer(wrongString.concat("9"));
        assertEquals(true, answerString.isCorrect());

        // Boolean answer
        genericType = new TypeToken<QuestAnswer<Boolean>>(){}.getType();
        QuestAnswer<Boolean> answerBool = gson.fromJson(gson.toJson(
                new QuestAnswer<>(true, "It is correct answer")), genericType);

        answerBool.setUserAnswer(true);
        assertEquals(true, answerBool.isCorrect());

        answerBool.setUserAnswer(false);
        assertEquals(false, answerBool.isCorrect());
    }

}