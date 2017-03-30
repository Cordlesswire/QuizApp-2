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

import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.quizapp.databinding.ActivityMainBinding;
import com.example.android.quizapp.databinding.QuestItemBinding;

import static android.view.View.inflate;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mMainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        QuestItemBinding itemBinding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.quest_item, mMainBinding.layoutQuestList, true);

        itemBinding.questID.setText("1.");
        itemBinding.questTitle.setText("Question #1");

        itemBinding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.quest_item, mMainBinding.layoutQuestList, true);

        itemBinding.questID.setText("2.");
        itemBinding.questTitle.setText("Question #2");

        /*View newItem = View.inflate(getBaseContext(), R.layout.quest_item, mMainBinding.layoutQuestList);
        ((TextView) newItem.findViewById(R.id.questID)).setText("1");
        ((TextView) newItem.findViewById(R.id.questTitle)).setText("Question #1");

        View newItem1 = View.inflate(getBaseContext(), R.layout.quest_item, mMainBinding.layoutQuestList);
        ((TextView) newItem1.findViewById(R.id.questID)).setText("2");
        ((TextView) newItem1.findViewById(R.id.questTitle)).setText("Question #2");

        View newItem2 = View.inflate(getBaseContext(), R.layout.quest_item, mMainBinding.layoutQuestList);
        ((TextView) newItem2.findViewById(R.id.questID)).setText("3");
        ((TextView) newItem2.findViewById(R.id.questTitle)).setText("Question #3");*/

    }
}
