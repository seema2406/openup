package com.openup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.openup.R;
import com.openup.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectGenderActivity extends AppCompatActivity {

    @BindView(R.id.select1)
    ImageView male;
    @BindView(R.id.select2)
    ImageView female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gender);
        ButterKnife.bind(this);
        male.setImageDrawable(getDrawable(R.mipmap.male));
        female.setImageDrawable(getDrawable(R.mipmap.female));

    }

    @OnClick(R.id.select1)
    public void selectMale(){
        Intent intent = new Intent(this, QuestionAnswerActivity.class);
        intent.putExtra("gender", Constants.MALE);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.select2)
    public void selectFemale(){
        Intent intent = new Intent(this, QuestionAnswerActivity.class);
        intent.putExtra("gender", Constants.FEMALE);
        startActivity(intent);
        finish();
    }
}
