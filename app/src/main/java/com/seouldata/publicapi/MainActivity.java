package com.seouldata.publicapi;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.net.*;
import android.widget.*;
import android.view.animation.*;

public class MainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView mainText1 = (TextView)findViewById(R.id.mainText1);
		TextView mainText2 = (TextView)findViewById(R.id.mainText2);
		LinearLayout mainButtons = (LinearLayout)findViewById(R.id.mainButtons);
		Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade);
		mainText1.startAnimation(fade);
		mainText2.startAnimation(fade);
		mainButtons.startAnimation(fade);
	}
	
	public void search(View v){
		startActivity(new Intent(this, InputActivity.class));
		/*
		if(isNetwork() == true) {
			startActivity(new Intent(this, InputActivity.class));
		}
		else {
			Toast.makeText(getApplicationContext(), "인터넷 연결 상태를 확인해주세요!", Toast.LENGTH_SHORT).show();
		}
		*/
	}
	
	public void devinfo(View v) {
		startActivity(new Intent(this, DevInfoActivity.class));
	}
	
	public void Notification(View v) {
		startActivity(new Intent(this, NotificationActivity.class));
	}
	
	private Boolean isNetwork() {
		try{
			ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
			boolean isMobileAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable();
			boolean isMobileConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
			boolean isWifiAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable();
			boolean isWifiConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

			if((isWifiAvailable && isWifiConnect) || (isMobileAvailable && isMobileConnect)) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e){
			return false;
		}
	}
}
