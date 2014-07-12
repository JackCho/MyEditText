package com.jit.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MyEditText extends RelativeLayout {
	
	//组合控件之一 输入框
	private EditText mEditText;
	//组合控件之一 删除键，负责删除输入框内容
	private ImageView mDelete;
	
	//输入框提示
	private CharSequence mHint;
	//输入框字体颜色
	private int mTextColor;
	//输入框字体大小
	private float mTextSize;
	//输入框背景色
	private int mBgColor;
	
	//输入框内容变化接口
	private MyTextWatcher mWatcher;
	
	public MyEditText(Context context) {
		this(context, null);
	}

	public MyEditText(Context context, AttributeSet attrs) {
		 this(context, attrs, 0);
	}

	public MyEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		TypedArray a = context.obtainStyledAttributes(attrs, 
						R.styleable.My_EditText, defStyle, 0);
		mHint = a.getText(R.styleable.My_EditText_text_hint);
	    mTextColor = a.getColor(R.styleable.My_EditText_text_color, 
	    				Color.BLACK);
	    mTextSize = a.getDimension(R.styleable.My_EditText_text_size, 15);
		mBgColor = a.getColor(R.styleable.My_EditText_bg_color,
						Color.parseColor("#aaaaaa"));
		a.recycle();
		
		//加载组合控件布局
		LayoutInflater.from(context).inflate(R.layout.my_edittext, this);
		mEditText = (EditText) findViewById(R.id.my_et);
		mDelete = (ImageView) findViewById(R.id.my_delete);
		
		//设置组合控件自定义属性
		if (!TextUtils.isEmpty(mHint)) {
			mEditText.setHint(mHint);
		}
		mEditText.setTextSize(mTextSize);
		mEditText.setTextColor(mTextColor);
		mEditText.setBackgroundColor(mBgColor);
		
		//删除键监听，点击删除输入框内容
		mDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mEditText.setText("");
				mDelete.setVisibility(View.GONE);
			}
		});
		
		//输入框内容监听，内容为空，隐藏删除键；反之，显示删除键
		mEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, 
					int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if (s != null && s.toString().trim() != null) {
					mDelete.setVisibility(View.VISIBLE);
				} else {
					mDelete.setVisibility(View.GONE);
				}
				
				if (mWatcher != null) {
					mWatcher.textChange(s);
				}
			}
		});
	}
	
	//获取输入框内容
	public String getText() {
		if (mEditText == null) {
			return null;
		}
		
		return mEditText.getText().toString();
	}
	
	//设置内容改变的回调接口
	public void setTextWatcher(MyTextWatcher watcher) {
		this.mWatcher = watcher;
	}
	
	public interface MyTextWatcher {
		void textChange(Editable s);
	}
	
}

