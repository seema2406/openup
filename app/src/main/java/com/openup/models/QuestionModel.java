package com.openup.models;

import com.openup.base.BaseModel;

public class QuestionModel extends BaseModel{
    private String value;
    private int image_url, answer_index;
    private QuestionModel question1;
    private QuestionModel question2;
    private boolean click;

    public QuestionModel(String value, int image_url) {
        this.value = value;
        this.image_url = image_url;
    }

    public QuestionModel(QuestionModel question1, QuestionModel question2,int answer_index, boolean click) {
        this.question1 = question1;
        this.question2 = question2;
        this.answer_index = answer_index;
        this.click = click;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getImage_url() {
        return image_url;
    }

    public void setImage_url(int image_url) {
        this.image_url = image_url;
    }

    public QuestionModel getQuestion1() {
        return question1;
    }

    public void setQuestion1(QuestionModel question1) {
        this.question1 = question1;
    }

    public QuestionModel getQuestion2() {
        return question2;
    }

    public void setQuestion2(QuestionModel question2) {
        this.question2 = question2;
    }

    public int getAnswer_index() {
        return answer_index;
    }

    public void setAnswer_index(int answer_index) {
        this.answer_index = answer_index;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }
}
