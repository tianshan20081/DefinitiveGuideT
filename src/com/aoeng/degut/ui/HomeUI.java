package com.aoeng.degut.ui;

import com.aoeng.degut.R;
import com.aoeng.degut.R.layout;
import com.aoeng.degut.R.menu;

import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeUI extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_ui);

		this.findViewById(R.id.btnAidl).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null ;
		switch (v.getId()) {
		case R.id.btnAidl:
			intent = new Intent(this, AidlUI.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
