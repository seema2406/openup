package com.openup.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.openup.utils.CustomFontHelper;

public class OpenUpEditText extends android.support.v7.widget.AppCompatEditText {

    public OpenUpEditText(Context context) {
        super(context);
    }


    public OpenUpEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontHelper.setCustomFont(this, context, attrs);

    }

    public OpenUpEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, context, attrs);

    }


}