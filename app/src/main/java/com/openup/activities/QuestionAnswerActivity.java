package com.openup.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openup.R;
import com.openup.adapters.QuestionsAdapter;
import com.openup.models.QuestionModel;
import com.openup.utils.CirclePageIndicator;
import com.openup.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionAnswerActivity extends AppCompatActivity {

    private String gender = "";
    ArrayList<QuestionModel> question = new ArrayList<>();
    private QuestionsAdapter question_adapter;

    @BindView(R.id.ques_ans_viewpager)
    ViewPager quesAnsViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);

        ButterKnife.bind(this);
        //get intent
        Intent intent = getIntent();
        if (intent != null){
            gender = intent.getStringExtra("gender");
        }

        prepareQuestions();

        question_adapter = new QuestionsAdapter(this, question);
        quesAnsViewPager.setAdapter(question_adapter);

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(quesAnsViewPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(6 * density);

    }

    private void prepareQuestions() {
        if (gender!=null && !gender.isEmpty() && gender.equalsIgnoreCase(Constants.FEMALE)){

            question.add(0,new QuestionModel(new QuestionModel("tom_boy_type",R.mipmap.male),new QuestionModel("cute_type",R.mipmap.female),0,false));
            question.add(0,new QuestionModel(new QuestionModel("short_hair",R.mipmap.female_hair_01),new QuestionModel("long_hair",R.mipmap.female_hair_02),0,false));
            question.add(1,new QuestionModel(new QuestionModel("friends",R.mipmap.common_01),new QuestionModel("howimetyourmother",R.mipmap.common_02),0,false));
            question.add(2,new QuestionModel(new QuestionModel("reading",R.mipmap.book),new QuestionModel("music",R.mipmap.guitar),0,false));
            question.add(3,new QuestionModel(new QuestionModel("dog",R.mipmap.dog),new QuestionModel("cat",R.mipmap.cat),0,false));
            //question.add(0,new QuestionModel(new QuestionModel("black",R.mipmap.b),new QuestionModel("pink",R.mipmap.female_hair_02)));
        }else{
            question.add(0,new QuestionModel(new QuestionModel("bike_1",R.mipmap.bike_01),new QuestionModel("bike_2",R.mipmap.bike_02),0,false));
            question.add(1,new QuestionModel(new QuestionModel("friends",R.mipmap.common_01),new QuestionModel("howimetyourmother",R.mipmap.common_02),0,false));
            question.add(2,new QuestionModel(new QuestionModel("mountain",R.mipmap.mountain),new QuestionModel("beach",R.mipmap.beach),0,false));
            question.add(3,new QuestionModel(new QuestionModel("sachin",R.mipmap.sachin),new QuestionModel("kohli",R.mipmap.kohli),0,false));
            question.add(4,new QuestionModel(new QuestionModel("clouth_01",R.mipmap.clouth_01),new QuestionModel("clouth_02",R.mipmap.clouth_02),0,false));
        }
    }
}
