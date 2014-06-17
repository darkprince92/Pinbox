package com.example.pinboxproject;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.Toast;

import com.example.pinboxproject.AddPinFragment2.SendHandler;
import com.example.pinboxproject.adapters.PagerAdapter;
import com.example.pinboxproject.apputils.Base64;
import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.apputils.MyThread;
import com.example.pinboxproject.entity.Category;
import com.example.pinboxproject.entity.Location;
import com.example.pinboxproject.entity.Pin;
import com.example.pinboxproject.entity.Settings;

public class AddPinActivity extends FragmentActivity implements AddPinFragment1.DataHandler,SendHandler {
	
	private static ViewPager pager;
	private PagerAdapter pagerAdapter;
	private ProgressDialog pd;
	ArrayList<NameValuePair> sendData;
	private static final int CAMERA_REQUEST = 1888;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_pin);
		if(Settings.loggedUser==null)
		{
			Intent intent=new Intent(AddPinActivity.this,LoginActivity.class);
			startActivity(intent);
		}
		
		pager = (ViewPager)findViewById(R.id.add_pin_pager);
		ArrayList<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(new AddPinFragment1());
		fragments.add(new AddPinFragment2());
		pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this, fragments);
		pager.setAdapter(pagerAdapter);
	}
	public static ViewPager getViewPager(){
		return pager;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_pin, menu);
		return true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// TODO Auto-generated method stub
		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            image.setImageBitmap(photo);
            String imageStr=imageToString(photo);
            AddPinFragment2 fragment=(AddPinFragment2)pagerAdapter.getItem(pager.getCurrentItem());
            fragment.getImageData(imageStr);
           
        }
	}
	String imageToString(Bitmap photo)
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		
		photo.compress(Bitmap.CompressFormat.PNG, 100, bytes);
		      

		byte[] ba = bytes.toByteArray();

		String ba1 = Base64.encodeBytes(ba);
		return ba1;
	}
	@Override
	public void addData(String title, int catId, Location loc) {
		// TODO Auto-generated method stub
		AddPinFragment2 fragment=(AddPinFragment2)pagerAdapter.getItem(pager.getCurrentItem()+1);
		fragment.getDataFromfirst(title,catId,loc);
		Toast.makeText(getApplicationContext(), "Title: "+title+", Cat Id: "+catId+" Location: "+loc.toString(), Toast.LENGTH_LONG).show();
		System.out.println(loc.toString());
	}

	@Override
	public void sendData(String title, int catId, Location loc) {
		// TODO Auto-generated method stub
		insertIntoSqlite(title, catId, loc, "");
		pd=ProgressDialog.show(AddPinActivity.this,"","Adding Pin...",true,false);
		sendData=new ArrayList<NameValuePair>();
		sendData.add(new BasicNameValuePair("name", title));
		sendData.add(new BasicNameValuePair("cat_id", catId+""));
		sendData.add(new BasicNameValuePair("lat", loc.getLatitude()+""));
		sendData.add(new BasicNameValuePair("long", loc.getLongitude()+""));
		sendData.add(new BasicNameValuePair("address", loc.getAddress()));
		sendData.add(new BasicNameValuePair("district", loc.getDistrict()));
		sendData.add(new BasicNameValuePair("thana", loc.getThana()));
//		sendData.add(new BasicNameValuePair("user", Settings.loggedUser.getId()+""));
		MyThread threadObj=new MyThread(handler, sendData,Settings.BASE_URL+"addPin/"+Settings.loggedUser.getId());
		threadObj.start();
	}
	Handler handler=new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			JSONObject responseObj=null;
			pd.dismiss();
			Bundle b=msg.getData();
			String status=null;
			System.out.println(b.toString());
			String response=b.getString("response");
			if(response.equals("Success"))
			{
				Toast.makeText(getApplicationContext(), "Pin Added successfully", Toast.LENGTH_LONG).show();
				Intent intent=new Intent(AddPinActivity.this,MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		}
	};
	
	private void insertIntoSqlite(String title, int catId, Location loc,String desc)
	{
		Pin p;
		Category c=new Category(catId, "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
	    //get current date time with Date()
		Date date = new Date();
		String dateStr=dateFormat.format(date);
		p=new Pin(loc, title, desc, Settings.loggedUser.getId()+"", dateStr, c, 0, 0);
		MyPrePopulatedDBHelper mdh=MyPrePopulatedDBHelper.getInstance(getApplicationContext(), "tik");
		mdh.insertPin(p);
		
		
	}

	@Override
	public void sendDataWithAdditional(String title, int catId, Location loc,
			String desc, String tags, String imageStr) {
		// TODO Auto-generated method stub
		insertIntoSqlite(title, catId, loc, desc);
		
		pd=ProgressDialog.show(AddPinActivity.this,"","Adding Pin...",true,false);
		sendData=new ArrayList<NameValuePair>();
		sendData.add(new BasicNameValuePair("name", title));
		sendData.add(new BasicNameValuePair("cat_id", catId+""));
		sendData.add(new BasicNameValuePair("lat", loc.getLatitude()+""));
		sendData.add(new BasicNameValuePair("long", loc.getLongitude()+""));
		sendData.add(new BasicNameValuePair("address", loc.getAddress()));
		sendData.add(new BasicNameValuePair("district", loc.getDistrict()));
		sendData.add(new BasicNameValuePair("thana", loc.getThana()));
		sendData.add(new BasicNameValuePair("desc", desc));
		if(imageStr!=null)
			sendData.add(new BasicNameValuePair("image", imageStr));
		sendData.add(new BasicNameValuePair("tags", loc.getThana()));
//		sendData.add(new BasicNameValuePair("user", Settings.loggedUser.getId()+""));
		MyThread threadObj=new MyThread(handler, sendData,Settings.BASE_URL+"addPin/"+Settings.loggedUser.getId());
		threadObj.start();
		
	}

}
