package com.example.pinboxproject;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinboxproject.CommentDialogFragment.CommentHandler;
import com.example.pinboxproject.apputils.ImageLoader;
import com.example.pinboxproject.apputils.MapSetup;
import com.example.pinboxproject.apputils.MyThread;
import com.example.pinboxproject.entity.Comment;
import com.example.pinboxproject.entity.Image;
import com.example.pinboxproject.entity.Pin;
import com.example.pinboxproject.entity.Settings;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PinDetailsActivity extends NavigationActivity implements CommentHandler {
	
	private GoogleMap map;
	private Button buttonComment;
	private Button buttonReport;
	private Button buttonUpVote,buttonDownVote;
	private TextView textTitle;
	private TextView textCategory;
	private TextView textUser;
	private TextView textNoPhoto,textDesc;
	Pin pin;
	private ProgressDialog pd;
	private ArrayList<NameValuePair> dataToSend;
	private ArrayList<Image> images;
	private HorizontalScrollView scrollImageList;
	

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pin_details);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pin_details, menu);
		return true;
	}*/

	@Override
	protected void createView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_pin_details);
		if(Settings.loggedUser==null)
		{
			Intent intent=new Intent(PinDetailsActivity.this,LoginActivity.class);
			startActivity(intent);
			
		}
		Bundle b = this.getIntent().getExtras();
		pin=this.getIntent().getParcelableExtra("pin_data");

		System.out.println("Pin Test"+pin);
		getImagesFromDB();
		init();
		
		
		
	}

	@Override
	protected void createMenu() {
		// TODO Auto-generated method stub
		
	}
	
	private void init(){

		MapSetup mapSetup = new MapSetup(this, R.id.pin_details_map_fragment);
		map = mapSetup.getMap();
		buttonComment = (Button)findViewById(R.id.pin_button_comment);
		buttonReport = (Button)findViewById(R.id.pin_button_report);
		buttonUpVote=(Button)findViewById(R.id.pin_button_up_vote);
		buttonDownVote=(Button)findViewById(R.id.pin_button_down_vote);
		textTitle = (TextView)findViewById(R.id.pin_text_title);
		textCategory = (TextView)findViewById(R.id.pin_text_category);
		textUser = (TextView)findViewById(R.id.pin_text_user);
		textNoPhoto = (TextView)findViewById(R.id.pin_text_no_photo);
		textDesc=(TextView)findViewById(R.id.pin_text_description);
		scrollImageList=(HorizontalScrollView)findViewById(R.id.horizontal_list);
		
		
		
		buttonUpVote.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateVote("up");
			}
		});
		buttonDownVote.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateVote("down");
			}
		});
		buttonComment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewComments();
			}
		});
		textTitle.setText(pin.getName());
		textCategory.setText(pin.getCat().getCatName());
		textUser.setText(pin.getUsername());
		textDesc.setText(pin.getDescription());
		map.clear();
		LatLng myLocation = new LatLng(pin.getLoc().getLatitude(),pin.getLoc().getLongitude());
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15));
		Marker m= map.addMarker(new MarkerOptions().position(myLocation)
				.title(pin.getLoc().getAddress()));
		
	}
	private void updateVote(String type)
	{
		pd=ProgressDialog.show(PinDetailsActivity.this,"","Voting...",true,false);
		dataToSend=new ArrayList<NameValuePair>();
		dataToSend.add(new BasicNameValuePair("user", Settings.loggedUser.getId()+""));
		dataToSend.add(new BasicNameValuePair("loc", pin.getId()+""));
		dataToSend.add(new BasicNameValuePair("vote", type));
		
		MyThread mt=new MyThread(handle, dataToSend, Settings.BASE_URL+"vote");
		mt.start();
	}
	private void showVote(int up,int down)
	{
		buttonUpVote.setText("Up("+up+")");
		buttonDownVote.setText("Down("+down+")");
	}
	private void viewComments(){
		
		pd=ProgressDialog.show(PinDetailsActivity.this,"","Fetching commments...",true,false);
		dataToSend=new ArrayList<NameValuePair>();
		dataToSend.add(new BasicNameValuePair("location_id", pin.getId()+""));
		
		MyThread mt=new MyThread(handle, dataToSend, Settings.BASE_URL+"getComments");
		mt.start();
		
		
	}
	private void getImagesFromDB()
	{
		pd=ProgressDialog.show(PinDetailsActivity.this,"","Fetching Images...",true,false);
		dataToSend=new ArrayList<NameValuePair>();
//		dataToSend.add(new BasicNameValuePair("location_id", pin.getId()+""));
		MyThread mt=new MyThread(handle, dataToSend, Settings.BASE_URL+"LocationImage/"+pin.getId());
		mt.start();
	}
	private void loadImage()
	{
		ImageLoader imgLoader=new ImageLoader(getApplicationContext());
		int loader=R.drawable.ic_launcher;
		
//		System.out.println(images.size());
		if(images!=null)
		{
			if(images.size()>0)
			{
				textNoPhoto.setVisibility(View.GONE);
				LinearLayout ll=(LinearLayout)findViewById(R.id.pin_ll_image_holder);
				
				for(int i=0;i<images.size();i++)
				{
					ImageView iv=new ImageView(this);
					imgLoader.DisplayImage(Settings.ROOT_URL+images.get(i).getThumb_path(), loader, iv);
					iv.setClickable(true);
					final int j=i;
					iv.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							ImageViewerDialogFragment ivdf=new ImageViewerDialogFragment(images.get(j).getPath());
							ivdf.show(getSupportFragmentManager(), ""+pin.getName());
							
						}
					});
					
					
					ll.addView(iv);
				}
//				scrollImageList.addView(ll);
				
			}
		}
//		int loader=R.drawable.no_photos;
		
//		imgLoader.DisplayImage(Settings.BASE_IMAGE_URL+imagePath, loader, iv);
	}
	Handler handle=new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			JSONObject responseObj=null;
			pd.dismiss();
			Bundle b=msg.getData();
			String status=null;
			System.out.println(b.toString());
			
			try {
				responseObj=new JSONObject(b.getString("response"));
				if(responseObj.getString("response_type").equals("comments"))
				{
					ArrayList<Comment> comments=new ArrayList<Comment>();
					JSONArray commentsJson=responseObj.getJSONArray("response");
					for(int i=0;i<commentsJson.length();i++)
					{
						Comment c;
						String commentDesc=commentsJson.getJSONObject(i).getString("COMMENT");
						String commentdate=commentsJson.getJSONObject(i).getString("COMMENT_DATE");
						c=new Comment("", commentdate, commentDesc);
						comments.add(c);
					}
					
					CommentDialogFragment dialog = new CommentDialogFragment(PinDetailsActivity.this,comments);
					dialog.show(getSupportFragmentManager(), "Comments");
				}
				else if(responseObj.getString("response_type").equals("no_comments"))
				{
//					Toast.makeText(getApplicationContext(), "No Comment Yet", Toast.LENGTH_LONG).show();
					CommentDialogFragment dialog = new CommentDialogFragment(PinDetailsActivity.this,null);
					dialog.show(getSupportFragmentManager(), "Comments");
					
				}
				else if(responseObj.getString("response_type").equals("success_add"))
				{
					viewComments();
				}
				else if(responseObj.getString("response_type").equals("success"))
				{
					int up=responseObj.getJSONObject("response").getInt("up_vote");
					int down=responseObj.getJSONObject("response").getInt("down_vote");
					showVote(up, down);
				}
				else if(responseObj.getString("response_type").equals("error"))
				{
					Toast.makeText(getApplicationContext(), responseObj.getString("response"), Toast.LENGTH_LONG).show();
				}
				else if(responseObj.getString("response_type").equals("success_in_getting_image"))
				{
					images=new ArrayList<Image>();
					JSONArray imageJsonArray=responseObj.getJSONArray("response");
					for(int i=0;i<imageJsonArray.length();i++)
					{
						String path=imageJsonArray.getJSONObject(i).getString("PATH");
						String thumbPath=imageJsonArray.getJSONObject(i).getString("THUMB_PATH");
						Image im=new Image(path, thumbPath);
						images.add(im);
					}
					loadImage();
					Toast.makeText(getApplicationContext(), "Has some Images", Toast.LENGTH_LONG).show();
				}
				else if(responseObj.getString("response_type").equals("error_in_getting_image"))
				{
					Toast.makeText(getApplicationContext(), "No Images", Toast.LENGTH_LONG).show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	};


	@Override
	public void getAddedComment(Comment comment) {
		// TODO Auto-generated method stub
		pd=ProgressDialog.show(PinDetailsActivity.this,"","Adding commments...",true,false);
		dataToSend=new ArrayList<NameValuePair>();
		dataToSend.add(new BasicNameValuePair("loc_id", pin.getId()+""));
		dataToSend.add(new BasicNameValuePair("user_id", Settings.loggedUser.getId()+""));
		dataToSend.add(new BasicNameValuePair("comment", comment.getDescription()));


		
		MyThread mt=new MyThread(handle, dataToSend, Settings.BASE_URL+"addComment");
		mt.start();
	}

}
