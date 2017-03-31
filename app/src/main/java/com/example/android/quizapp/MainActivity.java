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
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.quizapp.databinding.ActivityMainBinding;

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

        LinearLayout itemList = (LinearLayout) findViewById(R.id.layoutQuestList);

        /* 1 */
        EditText editText = (EditText) getLayoutInflater().inflate(R.layout.test_item, null);

        View item = getLayoutInflater().inflate(R.layout.quest_item, null);
        LinearLayout answerList = (LinearLayout) item.findViewById(R.id.layoutAnswers);
        answerList.addView(editText);

        itemList.addView(item);

        /* 2. Right Sequence */
        /* 2.1. Inflate row */
        editText = (EditText) getLayoutInflater().inflate(R.layout.test_item, null);

        /* 2.2. Inflate row container*/
        item = getLayoutInflater().inflate(R.layout.quest_item, null);
        ((TextView) (item.findViewById(R.id.questID))).setText("2.");
        ((TextView) (item.findViewById(R.id.questTitle))).setText("Second item");

        /* 2.3. Add row to row container*/
        answerList = (LinearLayout) item.findViewById(R.id.layoutAnswers);
        answerList.addView(editText);

        /* 2.4. Add container to container list*/
        itemList.addView(item);

//        /* 3 Wrong Sequence*/
//        item = getLayoutInflater().inflate(R.layout.quest_item, itemList);
//        ((TextView) (item.findViewById(R.id.questID))).setText("3.");
//        ((TextView) (item.findViewById(R.id.questTitle))).setText("Third item");
//        answerList = (LinearLayout) item.findViewById(R.id.layoutAnswers);
//        getLayoutInflater().inflate(R.layout.test_item, answerList);


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
        question.addAnswer(new QuestAnswerString("4", ""));
        mQuestions.add(question);

        question = new QuizQuestionRadio("Select one of the options below.");
        question.addAnswer(new QuestAnswerBoolean(true, "First option"));
        question.addAnswer(new QuestAnswerBoolean(false, "Second option"));
        question.addAnswer(new QuestAnswerBoolean(false, "Third option"));
        mQuestions.add(question);

        for (int i = 0; i < 10; i++) {
            question = new QuizQuestion("Select none, one or more of the options below.");
            question.addAnswer(new QuestAnswerBoolean(true, "First option"));
            question.addAnswer(new QuestAnswerBoolean(false, "Second option"));
            question.addAnswer(new QuestAnswerBoolean(true, "Third option"));
            mQuestions.add(question);
        }
    }
}
