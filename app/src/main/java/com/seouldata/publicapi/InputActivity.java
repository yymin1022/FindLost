package com.seouldata.publicapi;
import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.google.firebase.iid.*;
import com.google.firebase.messaging.*;
import java.io.*;

public class InputActivity extends AppCompatActivity
{
	String whereCode = "";
	String whatCode = "";
	
	Intent resultIntent, detailIntent;
	
	Fragment fragmentNum = new Fragment();
	CustomViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		
		resultIntent = new Intent(this, ResultActivity.class);
		detailIntent = new Intent(this, DetailInfoActivity.class);
		
		FirebaseInstanceId.getInstance().getToken();
		
		viewPager=(CustomViewPager)findViewById(R.id.inputViewpager);
		viewPager.setPagingDisabled();
		viewPager.setAdapter(new adapter(getSupportFragmentManager()));
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		super.onResume();
		viewPager.setCurrentItem(0);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			int currentItem = viewPager.getCurrentItem();
			switch(currentItem){
				case 0:
					finish();
					break;
				case 1:
					viewPager.setCurrentItem(0);
					break;
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public void whereB1(View v){
		whereCode = "b1";
		viewPager.setCurrentItem(1);
	}
	
	public void whereB2(View v){
		whereCode = "b2";
		viewPager.setCurrentItem(1);
	}
	
	public void whereS1(View v){
		whereCode = "s1";
		viewPager.setCurrentItem(1);
	}
	
	public void whereS2(View v){
		whereCode = "s2";
		viewPager.setCurrentItem(1);
	}
	
	public void whereS3(View v){
		whereCode = "s3";
		viewPager.setCurrentItem(1);
	}

	public void whereS4(View v){
		whereCode = "s4";
		viewPager.setCurrentItem(1);
	}

	public void whereT1(View v){
		whereCode = "t1";
		viewPager.setCurrentItem(1);
	}

	public void whereT2(View v){
		whereCode = "t2";
		viewPager.setCurrentItem(1);
	}
	
	public void whatBag(View v){
		if(whereCode==""){
			viewPager.setCurrentItem(0);
		}else{whatCode = getResources().getString(R.string.input_what_bag);
			SearchDialog();
		}
	}

	public void whatEtc(View v){
		if(whereCode==""){
			viewPager.setCurrentItem(0);
		}else{whatCode = getResources().getString(R.string.input_what_etc);
			SearchDialog();
		}
	}

	public void whatBackpack(View v){
		if(whereCode==""){
			viewPager.setCurrentItem(0);
		}else{whatCode = getResources().getString(R.string.input_what_backpack);
			SearchDialog();
		}
	}

	public void whatDoc(View v){
		if(whereCode==""){
			viewPager.setCurrentItem(0);
		}else{whatCode = getResources().getString(R.string.input_what_doc);
			SearchDialog();
		}
	}
	
	public void whatShopping(View v){
		if(whereCode==""){
			viewPager.setCurrentItem(0);
		}else{whatCode = getResources().getString(R.string.input_what_shopping);
			SearchDialog();
		}
	}
	
	public void whatClothes(View v){
		if(whereCode==""){
			viewPager.setCurrentItem(0);
		}else{whatCode = getResources().getString(R.string.input_what_clothes);
			SearchDialog();
		}
	}

	public void whatWallet(View v){
		if(whereCode==""){
			viewPager.setCurrentItem(0);
		}else{whatCode = getResources().getString(R.string.input_what_wallet);
			SearchDialog();
		}
	}

	public void whatBook(View v){
		if(whereCode==""){
			viewPager.setCurrentItem(0);
		}else{whatCode = getResources().getString(R.string.input_what_book);
			SearchDialog();
		}
	}

	public void whatFile(View v){
		if(whereCode==""){
			viewPager.setCurrentItem(0);
		}else{whatCode = getResources().getString(R.string.input_what_file);
			SearchDialog();
		}
	}

	public void whatPhone(View v){
		if(whereCode==""){
			viewPager.setCurrentItem(0);
		}else{whatCode = getResources().getString(R.string.input_what_phone);
			SearchDialog();
		}
	}
	
	public void WhereBack(View v) {
		finish();
	}
	
	public void WhatBack(View v) {
		viewPager.setCurrentItem(0);
	}
	
	public void SearchDialog() {
		final EditText search = new EditText(InputActivity.this);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("검색")
		.setMessage("선택사항 입니다!")
		.setView(search)
		.setPositiveButton("검색", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String searchValue = search.getText().toString();
					resultIntent.putExtra("search", searchValue);
					resultIntent.putExtra("what", whatCode);
					resultIntent.putExtra("where", whereCode);
					startActivity(resultIntent);
					dialog.dismiss();
				}
			})
		.setNeutralButton("건너뛰기", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					resultIntent.putExtra("what", whatCode);
					resultIntent.putExtra("where", whereCode);
					startActivity(resultIntent);
					dialog.dismiss();
				}
			})
		.setNegativeButton("취소", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					dialog.dismiss();
				}
			});
        AlertDialog dialog = builder.create();
        dialog.show();
	}
	
	private class adapter extends FragmentPagerAdapter {
		public adapter(FragmentManager fm) {
			super(fm);
		}
		@Override
		public Fragment getItem(int position) {
			if(position<0 || 2<=position)
				return null;
			switch (position){ 
				case 0:
					fragmentNum=new WhereFragment();
					break;
				case 1:
					fragmentNum=new WhatFragment();
					break;
			}
			return fragmentNum;
		}
		@Override
		public int getCount() {
			return 2;
		}
	}
}
