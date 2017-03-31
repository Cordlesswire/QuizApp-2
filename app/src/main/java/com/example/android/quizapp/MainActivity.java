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
 * Last modified 3/30/17 6:19 PM
 */

package com.example.android.quizapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import com.example.android.quizapp.databinding.ActivityMainBinding;
import com.example.android.quizapp.databinding.QuestCheckItemBinding;
import com.example.android.quizapp.databinding.QuestInputItemBinding;
import com.example.android.quizapp.databinding.QuestRadioItemBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mMainBinding;
    private ArrayList<QuizQuestion> mQuestions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load questions
        mQuestions = new ArrayList<>();
        loadTestQuestions();

/***********************************************************************************************/
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
/***********************************************************************************************/



//        //mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//

//
//        for (int i = 0; i < mQuestions.size(); i++) {
//            // Inflate question title
//            QuizQuestion question = mQuestions.get(i);
//
//            QuestItemBinding itemBinding = DataBindingUtil.inflate(getLayoutInflater(),
//                    R.layout.quest_item, mMainBinding.layoutQuestList, true);
//            itemBinding.questID.setText(String.format(getString(R.string.formatQuestID), i + 1));
//            itemBinding.questTitle.setText(question.getQuestion());

//            for (QuestAnswer answer : question) {
//                ViewGroup vg = (ViewGroup) item.findViewById(R.id.layoutAnswers);
//                getLayoutInflater().inflate(R.layout.quest_input_item, vg);
//            }


//            // Specify container where answers to be added
//            ViewGroup answerContainer = itemBinding.layoutAnswers;
//            boolean isRadio = false;
//            if (question instanceof QuizQuestionRadio) {
//                answerContainer = itemBinding.radioGroup;
//                isRadio = true;
//            } else {
//
//            }
//
//            // Inflate answers
//            for (QuestAnswer answer : question) {
//                if (answer instanceof QuestAnswerString) {
//                    QuestInputItemBinding answerBinding = DataBindingUtil.inflate(getLayoutInflater(),
//                            R.layout.quest_input_item, answerContainer, true);
//                    answerBinding.inputTextView.setText("");
//                } else {
//                    if (isRadio) {
//                        QuestRadioItemBinding answerBinding = DataBindingUtil.inflate(getLayoutInflater(),
//                                R.layout.quest_radio_item, answerContainer, true);
//                        answerBinding.radioButton.setText(answer.getGuess());
//                    } else {
//                        QuestCheckItemBinding answerBinding = DataBindingUtil.inflate(getLayoutInflater(),
//                                R.layout.quest_check_item, answerContainer, true);
//                        answerBinding.checkbox.setText(answer.getGuess());
//                    }
//                }
//            }
//        }
    }

    private void loadTestQuestions() {
        mQuestions.clear();

        QuizQuestion question = new QuizQuestion("Input the result of the expression 2+2 in the input field below.");
        question.addAnswer(new QuestAnswerString("4", "Guess who?"));
        mQuestions.add(question);

        question = new QuizQuestionRadio("Select one of the options below.");
        question.addAnswer(new QuestAnswerBoolean(true, "First option"));
        mQuestions.add(question);

        question = new QuizQuestion("Select none, one or more of the options below.");
        question.addAnswer(new QuestAnswerBoolean(true, "First option"));
        mQuestions.add(question);

//        question = new QuizQuestionRadio("Select one of the options below.");
//        question.addAnswer(new QuestAnswerBoolean(true, "First option"));
//        question.addAnswer(new QuestAnswerBoolean(false, "Second option"));
//        question.addAnswer(new QuestAnswerBoolean(false, "Third option"));
//        mQuestions.add(question);
//
//        for (int i = 0; i < 10; i++) {
//            question = new QuizQuestion("Select none, one or more of the options below.");
//            question.addAnswer(new QuestAnswerBoolean(true, "First option"));
//            question.addAnswer(new QuestAnswerBoolean(false, "Second option"));
//            question.addAnswer(new QuestAnswerBoolean(true, "Third option"));
//            mQuestions.add(question);
//        }
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
