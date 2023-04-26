package com.example.customview1a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class EditTextWithClear extends AppCompatEditText {

    Drawable mCLearButtonImage;

    private void init() {
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

                if(getCompoundDrawables()[2] != null){
                    float clearButtonStart = (getWidth()-getPaddingEnd()-mCLearButtonImage.getIntrinsicWidth());
                    boolean isClearButtonClicked = false;
                    if (event.getX() > clearButtonStart){
                        isClearButtonClicked = true;
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
