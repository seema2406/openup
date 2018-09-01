package com.openup.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.openup.R;
import com.openup.activities.AppMainActivity;
import com.openup.models.QuestionModel;

import java.util.ArrayList;

public class QuestionsAdapter extends PagerAdapter implements View.OnClickListener{


    private ArrayList<QuestionModel> question = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private ImageView imageView_01, imageView_02;
    //private int pos = 0;


    public QuestionsAdapter(Context context, ArrayList<QuestionModel> question) {

        this.context = context;
        this.question = question;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return question.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.item_questions, view, false);
        //pos = position;
        assert imageLayout != null;
        imageView_01 = imageLayout.findViewById(R.id.select1);
        imageView_02 = imageLayout.findViewById(R.id.select2);

        imageView_01.setImageDrawable(context.getDrawable(question.get(position).getQuestion1().getImage_url()));
        imageView_02.setImageDrawable(context.getDrawable(question.get(position).getQuestion2().getImage_url()));

        if (question.get(position).getAnswer_index() != 0){
            if (question.get(position).getAnswer_index() == 1) {
                imageView_01.setColorFilter(context.getResources().getColor(R.color.colorPrimary));
                imageView_02.setColorFilter(context.getResources().getColor(R.color.dark_gray));
            }else if (question.get(position).getAnswer_index() == 2) {
                imageView_01.setColorFilter(context.getResources().getColor(R.color.dark_gray));
                imageView_02.setColorFilter(context.getResources().getColor(R.color.colorPrimary));
            }
        }
        imageView_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* question.get(position).setAnswer_index(1);
                imageView_01.setColorFilter(context.getResources().getColor(R.color.colorPrimary));
                imageView_02.setColorFilter(context.getResources().getColor(R.color.dark_gray));*/
                question.get(position).setClick(true);
                Intent intent = new Intent(context, AppMainActivity.class);
                context.startActivity(intent);
            }
        });
        imageView_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*question.get(position).setAnswer_index(2);
                imageView_01.setColorFilter(context.getResources().getColor(R.color.dark_gray));
                imageView_02.setColorFilter(context.getResources().getColor(R.color.colorPrimary));*/
                question.get(position).setClick(true);
                Intent intent = new Intent(context, AppMainActivity.class);
                context.startActivity(intent);
            }
        });
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }


    @Override
    public void onClick(View v) {
        /*switch (v.getId()){
            case R.id.select1:
                question.get(pos).setAnswer_index(1);
                imageView_01.setColorFilter(context.getResources().getColor(R.color.colorPrimary));
                imageView_02.setColorFilter(context.getResources().getColor(R.color.dark_gray));
                break;
            case R.id.select2:
                question.get(pos).setAnswer_index(2);
                imageView_01.setColorFilter(context.getResources().getColor(R.color.dark_gray));
                imageView_02.setColorFilter(context.getResources().getColor(R.color.colorPrimary));
                break;
        }*/
    }
}
