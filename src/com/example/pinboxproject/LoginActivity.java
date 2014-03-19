package com.example.pinboxproject;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pinboxproject.entity.*;
import com.example.pinboxproject.apputils.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	Button loginButton;
	EditText emailText,passwordText;
	ProgressDialog pd;
	ArrayList<NameValuePair> dataToSend;
	Button buttonSignUp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
	}
	
	private void init()
	{
		loginButton=(Button)findViewById(R.id.login_button_login);
		buttonSignUp = (Button)findViewById(R.id.login_button_signup);
		emailText=(EditText)findViewById(R.id.login_edittext_username);
		passwordText=(EditText)findViewById(R.id.login_edittext_password);
		buttonSignUp=(Button)findViewById(R.id.login_button_signup);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkLogin();
			}
		});
		
		buttonSignUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);			
				LoginActivity.this.startActivity(intent);				
			}
		});
	}
	
	private void checkLogin()
	{
		String email,password;
		email=emailText.getText().toString();
		password=passwordText.getText().toString();
		if(!email.equals("")&&!password.equals(""))
		{
			pd=ProgressDialog.show(LoginActivity.this,"","Logging in...",true,false);
			dataToSend=new ArrayList<NameValuePair>();
			dataToSend.add(new BasicNameValuePair("email", email));
			dataToSend.add(new BasicNameValuePair("pass", password));
			MyThread threadObj=new MyThread(handler, dataToSend,Settings.BASE_URL+"login");
			threadObj.start();
		}
		else
		{
			//email or password field empty
			Toast.makeText(this, "All Fields not completed", Toast.LENGTH_SHORT).show();
		}
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
			try {
				responseObj=new JSONObject(b.getString("response"));
				status=responseObj.getString("status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(status.equals("Success"))
			{
				String name=null,email=null;
				int id=0;
				User user;
				try {
					name=responseObj.getString("user_name");
					email=responseObj.getString("email");
					id=responseObj.getInt("user_id");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user=new User(id, name, email);
				Settings.loggedUser=user;
				AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
				builder.setMessage("You are Successfully Logged In");
				builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(LoginActivity.this,MainActivity.class);
						startActivity(intent);
					}
				});
				AlertDialog dialog = builder.create();
				dialog.show();
			}
			else if(status.equals("Wrong"))
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
				builder.setMessage("Wrong Username or Password");
				builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
