package com.example.customview1a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.icu.text.Bidi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.ViewUtils;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;

import java.util.Locale;

public class EditTextWithClear extends AppCompatEditText {

    Drawable mCLearButtonImage;
    boolean isRightToLeft;


    private void init() {
        isRightToLeft = getResources().getBoolean(R.bool.is_right_to_left);
        mCLearButtonImage = ResourcesCompat.getDrawable(
                getResources(), R.drawable.ic_clear_opaque_24dp, null
        );

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showClearButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(getCompoundDrawablesRelative()[2] != null){
                    float clearButtonStart = (getWidth()-getPaddingEnd()-mCLearButtonImage.getIntrinsicWidth());
                    float clearButtonStart_rtl = (getPaddingEnd()+mCLearButtonImage.getIntrinsicWidth());
                    boolean isClearButtonClicked = false;
                    if (isRightToLeft){
                        System.out.println("apa itu le");
                        System.out.println("x value : "+event.getX());
                        System.out.println("width value : "+clearButtonStart_rtl);
                        if (event.getX() < clearButtonStart_rtl) {
                            isClearButtonClicked = true;
                        }
                    }else{

                        System.out.println("rtl : "+isRightToLeft);
                        System.out.println("x value : "+event.getX());
                        System.out.println("width value : "+clearButtonStart);
                        if (event.getX() > clearButtonStart){
                            isClearButtonClicked = true;
                        }
                    }





                    if (isClearButtonClicked){
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            mCLearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);
                            showClearButton();
                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            mCLearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);
                            getText().clear();
                            hideClearButton();
                            return true;
                        }
                    }
                    else {
                        return false;
                    }
                }
                return false;
            }
        });
    }


    public EditTextWithClear(@NonNull Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void showClearButton() {
        System.out.println("rootView : "+ViewUtils.isLayoutRtl(getRootView()));
        setCompoundDrawablesRelativeWithIntrinsicBounds(
                null, null, mCLearButtonImage, null
        );

    }

    private void hideClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(
                null, null, null, null
        );
    }


}
