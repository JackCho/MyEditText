package com.jit.lib;

import com.jit.lib.MyEditText.MyTextWatcher;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.TextView;

public class MainActivity extends Activity {

	private MyEditText mEditText;
	private TextView mShowContent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mEditText = (MyEditText) findViewById(R.id.et);
		mShowContent = (TextView) findViewById(R.id.et_content);
		
		mEditText.setTextWatcher(new MyTextWatcher() {
			
			@Override
			public void textChange(Editable s) {
				mShowContent.setText("MyEditText的内容是：" + s);
			}
		});
		
	}

}
