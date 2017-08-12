/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.miwok;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //    private View.OnClickListener openNumbersList = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//            Intent i = new Intent(getApplicationContext(),NumbersActivity.class);
//            startActivity(i);
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
//        // Set the content of the activity to use the activity_main.xml layout file
//        setContentView(R.layout.activity_main);
//
//        TextView Numbers = (TextView) findViewById(R.id.numbers);
//        TextView Family = (TextView) findViewById(R.id.family);
//        TextView Phrases = (TextView) findViewById(R.id.phrases);
//        TextView Colors = (TextView) findViewById(R.id.colors);
//
//        Numbers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), NumbersActivity.class);
//                startActivity(i);
//            }
//        });
//        Family.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), FamilyActivity.class);
//                startActivity(i);
//            }
//        });
//        Colors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), ColorsActivity.class);
//                startActivity(i);
//            }
//        });
//        Phrases.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), PhrasesActivity.class);
//                startActivity(i);
//            }
//        });
//    }

//    public void openNumbersList(View view) {
//        Intent i = new Intent(this,NumbersActivity.class);
//        startActivity(i);
//
//    }
//    public void openFamilyList(View view) {
//        Intent i = new Intent(this,FamilyActivity.class);
//        startActivity(i);
//    }
//    public void openColorsList(View view) {
//        Intent i = new Intent(this,ColorsActivity.class);
//        startActivity(i);
//    }
//    public void openPhrasesList(View view) {
//        Intent i = new Intent(this,PhrasesActivity.class);
//        startActivity(i);
//    }
//}
