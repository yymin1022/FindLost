package com.seouldata.publicapi;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.webkit.*;
import android.util.*;
import android.content.*;
import org.w3c.dom.*;
import java.net.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import java.io.*;
import org.json.*;

public class ResultActivity extends Activity 
{
	String defaultUrl = "http://openapi.seoul.go.kr:8088/464d62696b79796d35316b71567a4f/json/SearchLostArticleService/1/1000/";
	String whereString = "";
	String whatString = "";
	String searchString = "";
	String fullUrl = "";
	String jsonPage;
	
	ListView listview ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
		Intent resultIntent = getIntent();
		whereString = resultIntent.getExtras().getString("where");
		whatString = resultIntent.getExtras().getString("what");
		searchString = resultIntent.getExtras().getString("search");
		if(searchString == null) {
			fullUrl = defaultUrl + whatString + "/" + whereString + "/";
		}
		else {
			fullUrl = defaultUrl + whatString + "/" + whereString + "/" + searchString;
		}
		
		TextView WhereText = (TextView)findViewById(R.id.Text_Where);
		if(whereString.equals("b1")) {
			WhereText.setText("시내버스");
		}
		else if(whereString.equals("b2")) {
			WhereText.setText("마을버스");
		}
		else if(whereString.equals("s1")) {
			WhereText.setText("지하철(1~4호선)");
		}
		else if(whereString.equals("s2")) {
			WhereText.setText("지하철(5~8호선)");
		}
		else if(whereString.equals("s3")) {
			WhereText.setText("코레일");
		}
		else if(whereString.equals("s4")) {
			WhereText.setText("지하철(9호선)");
		}
		else if(whereString.equals("t1")) {
			WhereText.setText("법인택시");
		}
		else if(whereString.equals("t2")) {
			WhereText.setText("개인택시");
		}
		new GetXMLTask().execute();
	}
	
	private class GetXMLTask extends AsyncTask<String, Void, ListViewAdapter>{	

		ProgressDialog asyncDialog = new ProgressDialog(ResultActivity.this);

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
		protected ListViewAdapter doInBackground(String... urls) {
			jsonPage = getStringFromUrl(fullUrl);
			ListViewAdapter adapter;
			adapter = new ListViewAdapter() ;
			try {
				JSONObject json = (new JSONObject(jsonPage)).getJSONObject("SearchLostArticleService");;
				JSONArray jArr = json.getJSONArray("row");
				for(int i=0; i<jArr.length(); i++){
					json = jArr.getJSONObject(i);
					String name = json.getString("GET_NAME");
					String place = json.getString("TAKE_PLACE");
					String from = json.getString("GET_POSITION");
					String date = json.getString("GET_DATE");
					String id = json.getString("ID");
					if(name==null){
						name = "NoResult";
					}
					if(place==null){
						place = "NoResult";
					}
					if(from==null){
						from = "NoResult";
					}
					if(date==null){
						date = "NoResult";
					}
					if(id==null){
						id = "NoResult";
					}
					if(name!="No Result"&&place!="No Result"&&id!="No Result"){
						adapter.addItem(name, place, from, date, id);
					}
				}
			}catch(Exception e){}
			return adapter;
		}

		@Override
		protected void onPostExecute(ListViewAdapter adapter) {
			listview = (ListView) findViewById(R.id.resultListView);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position, long id)
				{
					ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
					Intent intent = new Intent(ResultActivity.this, DetailInfoActivity.class);
					intent.putExtra("item_place", item.getPlace());
					intent.putExtra("item_from", item.getFrom());
					intent.putExtra("item_date", item.getDate());
					intent.putExtra("item_id", item.getId());
					intent.putExtra("where", whereString);
					startActivity(intent);
				}
			});
			asyncDialog.dismiss();
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
