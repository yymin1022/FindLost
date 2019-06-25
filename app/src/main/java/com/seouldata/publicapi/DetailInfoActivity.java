package com.seouldata.publicapi;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.net.*;

public class DetailInfoActivity extends AppCompatActivity
{
	String itemName = "";
	String itemPlace = "";
	String itemFrom = "";
	String itemDate = "";
	String itemId = "";
	
	String whereString = "";
	
	String number = "";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_info);
		Intent intent = getIntent();
		itemName = intent.getStringExtra("item_name");
		itemPlace = intent.getStringExtra("item_place");
		itemFrom = intent.getStringExtra("item_from");
		itemDate = intent.getStringExtra("item_date");
		itemId = intent.getStringExtra("item_id");
		TextView nameText = (TextView)findViewById(R.id.text_name);
		nameText.setText(itemName);
		TextView placeText = (TextView)findViewById(R.id.text_place);
		placeText.setText(itemPlace);
		TextView fromText = (TextView)findViewById(R.id.text_from);
		fromText.setText(itemFrom);
		TextView dateText = (TextView)findViewById(R.id.text_date);
		dateText.setText(itemDate);
		TextView idText = (TextView)findViewById(R.id.text_id);
		idText.setText(itemId);
		
		LinearLayout layout = (LinearLayout)findViewById(R.id.layout_detail_info);
		Button call1 = (Button)findViewById(R.id.call1);
		Button call2 = (Button)findViewById(R.id.call2);
		whereString = intent.getStringExtra("where");
		if(whereString.equals("b1")) {
			call1.setText("전화걸기(시내버스)");
			layout.removeView(call2);
		}
		else if(whereString.equals("b2")) {
			call1.setText("전화걸기(마을버스)");
			layout.removeView(call2);
		}
		else if(whereString.equals("s1")) {
			call1.setText("전화걸기(1,2 호선 시청역)");
			call2.setText("전화걸기(3,4 호선 충무로역)");
		}
		else if(whereString.equals("s2")) {
			call1.setText("전화걸기(5,8 호선 왕십리역)");
			call2.setText("전화걸기(6,7 호선 태릉입구역)");
		}
		else if(whereString.equals("s3")) {
			call1.setText("전화걸기(한국철도공사)");
			layout.removeView(call2);
		}
		else if(whereString.equals("s4")) {
			call1.setText("전화걸기(Metro9 고객센터)");
			layout.removeView(call2);
		}
		else if(whereString.equals("t1")) {
			call1.setText("전화걸기(법인택시)");
			layout.removeView(call2);
		}
		else if(whereString.equals("t2")) {
			call1.setText("전화걸기(개인택시)");
			layout.removeView(call2);
		}
	}
	
	public void photo(View v) {
		Intent intent = new Intent(DetailInfoActivity.this, PhotoActivity.class);
		intent.putExtra("item_id", itemId);
		startActivity(intent);
	}
	
	public void call1(View v) {
		if(whereString.equals("b1")) {
			number = "tel:024154101";
			try{
				Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse(number));
				startActivity(intent);
			}catch(ActivityNotFoundException e){
				Toast.makeText(getApplicationContext(), "오류가 발생하여 전화를 걸 수 없습니다.", Toast.LENGTH_LONG).show();
			}
		}
		else if(whereString.equals("b2")) {
			number = "tel:0231423002";
			try{
				Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse(number));
				startActivity(intent);
			}catch(ActivityNotFoundException e){
				Toast.makeText(getApplicationContext(), "오류가 발생하여 전화를 걸 수 없습니다.", Toast.LENGTH_LONG).show();
			}
		}
		else if(whereString.equals("s1")) {
			number = "tel:0261101122";
			try{
				Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse(number));
				startActivity(intent);
			}catch(ActivityNotFoundException e){
				Toast.makeText(getApplicationContext(), "오류가 발생하여 전화를 걸 수 없습니다.", Toast.LENGTH_LONG).show();
			}
		}
		else if(whereString.equals("s2")) {
			number = "tel:0263116765";
			try{
				Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse(number));
				startActivity(intent);
			}catch(ActivityNotFoundException e){
				Toast.makeText(getApplicationContext(), "오류가 발생하여 전화를 걸 수 없습니다.", Toast.LENGTH_LONG).show();
			}
		}
		else if(whereString.equals("s3")) {
			number = "tel:15447788";
			try{
				Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse(number));
				startActivity(intent);
			}catch(ActivityNotFoundException e){
				Toast.makeText(getApplicationContext(), "오류가 발생하여 전화를 걸 수 없습니다.", Toast.LENGTH_LONG).show();
			}
		}
		else if(whereString.equals("s4")) {
			number = "tel:0226560009";
			try{
				Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse(number));
				startActivity(intent);
			}catch(ActivityNotFoundException e){
				Toast.makeText(getApplicationContext(), "오류가 발생하여 전화를 걸 수 없습니다.", Toast.LENGTH_LONG).show();
			}
		}
		else if(whereString.equals("t1")) {
			number = "tel:0220339200";
			try{
				Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse(number));
				startActivity(intent);
			}catch(ActivityNotFoundException e){
				Toast.makeText(getApplicationContext(), "오류가 발생하여 전화를 걸 수 없습니다.", Toast.LENGTH_LONG).show();
			}
		}
		else if(whereString.equals("t2")) {
			number = "tel:0220846300";
			try{
				Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse(number));
				startActivity(intent);
			}catch(ActivityNotFoundException e){
				Toast.makeText(getApplicationContext(), "오류가 발생하여 전화를 걸 수 없습니다.", Toast.LENGTH_LONG).show();
			}
		}
	}
	
	public void call2(View v) {
		
		if(whereString.equals("s1")) {
			number = "tel:0261103344";
			try{
				Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse(number));
				startActivity(intent);
			}catch(ActivityNotFoundException e){
				Toast.makeText(getApplicationContext(), "오류가 발생하여 전화를 걸 수 없습니다.", Toast.LENGTH_LONG).show();
			}
		}
		else if(whereString.equals("s2")) {
			number = "tel:0263116766";
			try{
				Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse(number));
				startActivity(intent);
			}catch(ActivityNotFoundException e){
				Toast.makeText(getApplicationContext(), "오류가 발생하여 전화를 걸 수 없습니다.", Toast.LENGTH_LONG).show();
			}
		}
	}
}
