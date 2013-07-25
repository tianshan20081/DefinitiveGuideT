package com.aoeng.degut.ui;

import java.util.Map;

import com.aoeng.degu.aidl.IMyService;
import com.aoeng.degu.aidl.IProductService;
import com.aoeng.degu.aidl.Product;
import com.aoeng.degut.R;
import com.aoeng.degut.utils.ViewUtils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AidlUI extends Activity implements OnClickListener {
	private Button btnCallAidl;
	private IMyService myService;
	private ServiceConnection serviceConnection;
	private Button btnCallComplexAidl;
	private IProductService productService;
	private ServiceConnection productConnection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_aidl_home);
		btnCallAidl = (Button) this.findViewById(R.id.btnCallAidl);
		btnCallAidl.setEnabled(false);
		btnCallComplexAidl = (Button) this.findViewById(R.id.btnCallComplexAidl);
		btnCallComplexAidl.setEnabled(false);
		this.findViewById(R.id.btnBindAidl).setOnClickListener(this);
		this.findViewById(R.id.btnCallAidl).setOnClickListener(this);
		this.findViewById(R.id.btnBindComplexAidl).setOnClickListener(this);
		this.findViewById(R.id.btnCallComplexAidl).setOnClickListener(this);

		serviceConnection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				myService = null;
				ViewUtils.toastCenter(AidlUI.this, "Service Failed", false);
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				// 获得服务对象
				myService = IMyService.Stub.asInterface(service);
				ViewUtils.toastCenter(AidlUI.this, "Service Connection Success !", false);
				btnCallAidl.setEnabled(true);
			}
		};

		productConnection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				productService = null;
				ViewUtils.toastCenter(AidlUI.this, "Service Fialed", false);
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				productService = IProductService.Stub.asInterface(service);
				ViewUtils.toastCenter(AidlUI.this, "Service Connection Success", false);
			}
		};

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnBindComplexAidl:
			bindService(new Intent("com.aoeng.degu.aidl.ProductService"), productConnection, Context.BIND_AUTO_CREATE);
			ViewUtils.toastCenter(this, "绑定复杂对象", false);
			btnCallComplexAidl.setEnabled(true);
			break;
		case R.id.btnCallComplexAidl:
			try {
				ViewUtils.toastCenter(this, "调用复杂对象", false);
				ViewUtils.toastCenter(this, productService.getProduct().toString(), false);
				ViewUtils.toastCenter(this, productService.getMap("China", productService.getProduct()).toString(), false);

			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case R.id.btnBindAidl:
			bindService(new Intent("com.aoeng.degu.aidl.IMyService"), serviceConnection, Context.BIND_AUTO_CREATE);
			ViewUtils.toastCenter(this, "绑定简单AIDL 服务", false);
			break;
		case R.id.btnCallAidl:
			try {
				ViewUtils.toastCenter(AidlUI.this, "调用简单AIDL 服务" + myService.getValue(), false);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
}
