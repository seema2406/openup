package com.openup.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.openup.utils.CustomFontHelper;

public class OpenUpButton extends android.support.v7.widget.AppCompatButton {


    /**
     * @param context
     */
    public OpenUpButton(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public OpenUpButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, context, attrs);

    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public OpenUpButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        CustomFontHelper.setCustomFont(this, context, attrs);

    }

}