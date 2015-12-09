package com.tv.orangetv;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import com.tv.orangetv.tvs.Tvs;
import com.tv.orangetv.utils.HttpUtils;
import com.tv.orangetv.adapter.TvAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends Activity {

	
	private ListView lvtv;
	private TvAdapter adapter;
	
	private List<Tvs>  tvsList;
	
	public static final String GET_NEWS_URL ="http://192.168.65.130:3000/tv/json";
	
	private Handler getNewsHandler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			String jsonData = (String) msg.obj;
			System.out.println(jsonData);
			try {
				JSONArray jsonArray = new JSONArray(jsonData);
				for (int i=0;i<jsonArray.length();i++){
					JSONObject object = jsonArray.getJSONObject(i);
					String title = object.getString("video_topic");
					String desc = object.getString("video_describle");
					String time = object.getString("video_up_time");
					String content_url = object.getString("video_url");
					String pic_url = object.getString("video_img_url");
										
					tvsList.add(new Tvs(title, desc, time, content_url, pic_url));
				}
				adapter.notifyDataSetChanged();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		
		
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvtv= (ListView)findViewById(R.id.lvTv);
        tvsList=new ArrayList<Tvs>();
        adapter=new TvAdapter(this,tvsList);
        lvtv.setAdapter(adapter);    
          
        
        HttpUtils.getTvJSON(GET_NEWS_URL, getNewsHandler);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
