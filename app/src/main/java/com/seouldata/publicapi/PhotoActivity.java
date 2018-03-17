package com.seouldata.publicapi;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.widget.*;
import java.io.*;
import java.net.*;
import org.json.*;
import com.github.chrisbanes.photoview.*;

public class PhotoActivity extends AppCompatActivity
{
	PhotoView imgView;
	Bitmap bm;
	
	String photoUrl = "";
	String itemFrom = "";
	String imgURL = "";
	String fullUrl = "http://openapi.seoul.go.kr:8088/52564b777879796d383372636c6e73/json/SearchLostArticleImageService/1/5/";
	String jsonPage;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		Intent intent = getIntent();
		fullUrl = fullUrl + intent.getStringExtra("item_id");
		new getJSONtask().execute(fullUrl);
	}
	
	private class getJSONtask extends AsyncTask<String, Void, String>{	

		ProgressDialog asyncDialog = new ProgressDialog(PhotoActivity.this);

		@Override
		protected void onPreExecute()
		{
			asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("로딩중입니다..");
			asyncDialog.setCancelable(false);
            asyncDialog.show();
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(String... urls) {
			jsonPage = getStringFromUrl(fullUrl);
			try {
				JSONObject json = (new JSONObject(jsonPage)).getJSONObject("SearchLostArticleImageService");;
				JSONArray jArr = json.getJSONArray("row");
				for(int i=0; i<jArr.length(); i++){
					json = jArr.getJSONObject(i);
					imgURL = json.getString("IMAGE_URL");
					if(imgURL==null){
						imgURL = "No Result";
					}
				}
			}catch(Exception e){}
			return imgURL;
		}

		@Override
		protected void onPostExecute(String result)
		{
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						imgView = (PhotoView)findViewById(R.id.resultImageView);
						URL url = new URL(imgURL);
						URLConnection conn = url.openConnection();
						conn.connect();
						BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
						bm = BitmapFactory.decodeStream(bis);
						bis.close();
						asyncDialog.dismiss();
					}catch(Exception e){
						Log.e("Image View Error", e.toString());
						asyncDialog.dismiss();
						Handler mHandler = new Handler(Looper.getMainLooper());
						mHandler.postDelayed(new Runnable() {
							@Override
							public void run() {
								android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(PhotoActivity.this);
								alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
											dialog.dismiss();
											finish();
										}
									});
								alert.setMessage("등록된 사진이 없습니다.");
								alert.setCancelable(false);
								alert.show();
							}
						}, 0);
					}
					new Thread(new Runnable() {
						@Override
						public void run() {    
							runOnUiThread(new Runnable(){
									@Override
									public void run() {
										imgView.setImageBitmap(bm);
									}
								});
						}
					}).start();
				}
			});
		t.start();
		super.onPostExecute(result);
	}
}

	public String getStringFromUrl(String pUrl){
		BufferedReader bufreader = null;
		HttpURLConnection urlConnection = null;
		StringBuffer page = new StringBuffer();
		try{
			URL url = new URL(pUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream contentStream = urlConnection.getInputStream();
			bufreader = new BufferedReader(new InputStreamReader(contentStream, "UTF-8"));
			String line = null;
			while((line = bufreader.readLine())!=null){
				page.append(line);
			}
		} catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				bufreader.close();
				urlConnection.disconnect();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return page.toString();
	}
}
