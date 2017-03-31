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
 * Last modified 3/31/17 9:27 PM
 */

package com.example.android.quizapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.quizapp.databinding.QuestCheckItemBinding;
import com.example.android.quizapp.databinding.QuestInputItemBinding;
import com.example.android.quizapp.databinding.QuestRadioItemBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<QuizQuestion> mQuestions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load questions
        mQuestions = new ArrayList<>();
        loadTestQuestions();

        setContentView(R.layout.activity_main);

        /* Inflate the Layout of the Questions*/
        LinearLayout itemList = (LinearLayout) findViewById(R.id.layoutQuestList);

        for (int i = 0; i < mQuestions.size(); i++) {
            /* Inflate Question Item View */
            View item = getLayoutInflater().inflate(R.layout.quest_item, itemList, false);

            /* Update Question Item Title and ID */
            QuizQuestion question = mQuestions.get(i);
            ((TextView) (item.findViewById(R.id.questID))).setText(
                    String.format(getString(R.string.formatQuestID), i + 1));
            ((TextView) (item.findViewById(R.id.questTitle))).setText(question.getQuestion());

            /* Get reference to the Layout of answers */
            LinearLayout answerList = (LinearLayout) item.findViewById(R.id.layoutAnswers);

            /* Get reference to subLayout for radio buttons */
            RadioGroup radioAnswers = (RadioGroup) answerList.findViewById(R.id.radioAnswers);

            /* Inflate Answers into the layout of answers */
            int ansCounter = 0;
            for (QuestAnswer answer : question) {
                /* Inflate view type depending on answer and question type */
                if (answer instanceof QuestAnswerString) {
                    QuestInputItemBinding ansBinding =
                            QuestInputItemBinding.inflate(getLayoutInflater(), answerList, false);
                    ansBinding.setAnswer(answer);
                    answerList.addView(ansBinding.getRoot());
                } else {
                    if (question instanceof QuizQuestionRadio) {
                        QuestRadioItemBinding ansBinding =
                                QuestRadioItemBinding.inflate(getLayoutInflater(), radioAnswers, false);
                        ansBinding.radioButton.setId(++ansCounter);
                        ansBinding.setAnswer(answer);
                        radioAnswers.addView(ansBinding.getRoot());
                    } else {
                        QuestCheckItemBinding ansBinding =
                                QuestCheckItemBinding.inflate(getLayoutInflater(), answerList, false);
                        ansBinding.setAnswer(answer);
                        answerList.addView(ansBinding.getRoot());
                    }
                }
            }

            /* Finally inflate Question Item into the Layout of the Questions*/
            itemList.addView(item);
        }
    }

    private void loadTestQuestions() {
        mQuestions.clear();

        QuizQuestion question = new QuizQuestion("Input the result of the expression 2+2 in the input field below.");
        question.addAnswer(new QuestAnswerString("4", ""));
        mQuestions.add(question);

        question = new QuizQuestion("Input the result of the expression 2 + 2 * 2 in the input field below.");
        question.addAnswer(new QuestAnswerString("6", ""));
        mQuestions.add(question);

        question = new QuizQuestion("Select right ways to obtain reference to the View with ID 'text_view_score' from java source");
        question.addAnswer(new QuestAnswerBoolean(false, "TextView scoreView = (TextView) setContentView(R.layout.text_view_score);"));
        question.addAnswer(new QuestAnswerBoolean(false, "TextView scoreView = findViewById(R.id.text_view_score);"));
        question.addAnswer(new QuestAnswerBoolean(true, "TextView scoreView = (TextView) findViewById(R.id.text_view_score);"));
        mQuestions.add(question);

        question = new QuizQuestion("Select proper values for attribute 'layout_width' in xml file");
        question.addAnswer(new QuestAnswerBoolean(true, "\"100px\""));
        question.addAnswer(new QuestAnswerBoolean(false, "100dp"));
        question.addAnswer(new QuestAnswerBoolean(true, "\"fill_parent\""));
        mQuestions.add(question);

        question = new QuizQuestionRadio("Which attribute is used to specify particular image for ImageView in xml?");
        question.addAnswer(new QuestAnswerBoolean(true, "src"));
        question.addAnswer(new QuestAnswerBoolean(false, "img"));
        question.addAnswer(new QuestAnswerBoolean(false, "res"));
        mQuestions.add(question);

        question = new QuizQuestionRadio("Which method is in charge of inflating layout content from xml?");
        question.addAnswer(new QuestAnswerBoolean(false, "findLayoutById(...)"));
        question.addAnswer(new QuestAnswerBoolean(false, "loadLayout(...)"));
        question.addAnswer(new QuestAnswerBoolean(true, "setContentView(...)"));
        mQuestions.add(question);


    }

    public void checkAnswers(View view) {
        int correct = 0;
        for (QuizQuestion quest : mQuestions) {
            if (quest.isCorrect()) correct++;
        }

        Toast.makeText(getApplicationContext(),
                String.format(getString(R.string.toast_results), correct, mQuestions.size()),
                Toast.LENGTH_SHORT).show();
    }
}
