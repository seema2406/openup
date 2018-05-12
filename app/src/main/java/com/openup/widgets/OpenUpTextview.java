package com.openup.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by acer on 12/13/2017.
 */

public class OpenUpTextview extends TextView {


    public OpenUpTextview(Context context) {
        super(context);
    }


    public OpenUpTextview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontHelper.setCustomFont(this, context, attrs);

    }

    public OpenUpTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, context, attrs);

    }
}
