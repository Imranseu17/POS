package com.example.root.pos.model;

import android.app.Dialog;
import android.content.Context;

import android.widget.TextView;

import com.example.root.pos.R;


public class ChooseAlertDialog extends Dialog {


    private TextView mTitleTv, mContentTv, mPositiveBtn, mNegativeBtn;
    private OnPositiveListener mOnPositiveListener;
    private OnNegativeListener mOnNegativeListener;

    private int mDialogType;
    private boolean mIsShowAnim;
    private CharSequence mTitle, mContent, mBtnText, mNegText;


    public ChooseAlertDialog(Context context) {
        this(context,0);
    }

    public ChooseAlertDialog(Context context, int theme) {
        super(context, R.style.color_dialog);
    }

    public ChooseAlertDialog setAnimationEnable(boolean enable) {
         mIsShowAnim = enable;
        return this;
    }

    public ChooseAlertDialog setTitleText(CharSequence title) {
        mTitle = title;
        return this;
    }

    public ChooseAlertDialog setTitleText(int resId) {
        return setTitleText(getContext().getString(resId));
    }

    public ChooseAlertDialog setContentText(CharSequence content) {
        mContent = content;
        return this;
    }

    public ChooseAlertDialog setContentText(int resId) {
        return setContentText(getContext().getString(resId));
    }

    public TextView getTitleTextView() {
        return mTitleTv;
    }

    public TextView getContentTextView() {
        return mContentTv;
    }

    public ChooseAlertDialog setDialogType(int type) {
        mDialogType = type;
        return this;
    }

    public int getDialogType() {
        return mDialogType;
    }

    public ChooseAlertDialog setPositiveListener(CharSequence btnText, OnPositiveListener l) {
        mBtnText = btnText;
        return setPositiveListener(l);
    }

    public ChooseAlertDialog setPositiveListener(int stringResId, OnPositiveListener l) {
        return setPositiveListener(getContext().getString(stringResId), l);
    }

    public ChooseAlertDialog setPositiveListener(OnPositiveListener l) {
        mOnPositiveListener = l;
        return this;
    }

    public ChooseAlertDialog setNegativeListener(CharSequence btnText, OnNegativeListener l) {
        mNegText = btnText;
        return setNegativeListener(l);
    }



    public ChooseAlertDialog setNegativeListener(OnNegativeListener l) {
        mOnNegativeListener = l;
        return this;
    }



    public interface OnPositiveListener {
        void onClick(ChooseAlertDialog dialog);
    }

    public interface OnNegativeListener {
        void onClick(ChooseAlertDialog dialog);
    }

}
