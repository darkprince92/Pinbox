package com.example.pinboxproject;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pinboxproject.apputils.MyThread;
import com.example.pinboxproject.entity.Settings;
import com.example.pinboxproject.entity.User;

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

public class SignUpActivity extends Activity {
	
	private Button buttonSignUp;
	private EditText editTextUsername;
	private EditText editTextEmail;
	private EditText editTextPassword;
	private EditText editTextConfirmPassword;
	private ProgressDialog pd;
	private ArrayList<NameValuePair> dataToSend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	
	private void init(){
		buttonSignUp = (Button)findViewById(R.id.signup_button_signup);
		editTextUsername = (EditText)findViewById(R.id.signup_edittext_username);
		editTextEmail = (EditText)findViewById(R.id.signup_edittext_email);
		editTextPassword = (EditText)findViewById(R.id.signup_edittext_password);
		editTextConfirmPassword = (EditText)findViewById(R.id.signup_edittext_confrm_password);
		
		buttonSignUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkSignUp();				
			}
		});		
	}
	
	private void checkSignUp(){
		String username, email, password, confirmPassword;
		username = editTextUsername.getText().toString();
		email = editTextEmail.getText().toString();
		password = editTextPassword.getText().toString();
		confirmPassword = editTextConfirmPassword.getText().toString();
		
		System.out.println(username + ":" + email + ":" + password + ":" + confirmPassword);
		if(isValidEmail(editTextEmail.getText())){
			System.out.println("email valid");
		}else {
			System.out.println("email invalid");
		}
		
		if(username.equals("") || password.equals("") || !password.equals(confirmPassword)
				|| !(isValidEmail(editTextEmail.getText()))){
			Toast.makeText(this, "please fill up the form correctly", Toast.LENGTH_SHORT).show();
			return;
		}
		
		pd=ProgressDialog.show(SignUpActivity.this,"","Signing Up...",true,false);
		dataToSend=new ArrayList<NameValuePair>();
		dataToSend.add(new BasicNameValuePair("username", username));
		dataToSend.add(new BasicNameValuePair("email", email));
		dataToSend.add(new BasicNameValuePair("pass", password));
		MyThread threadObj=new MyThread(handler, dataToSend,Settings.BASE_URL+"Register");
		threadObj.start();
	}
	
	Handler handler=new Handler(){
		
		public void handleMessage(android.os.Message msg){
			JSONObject responseObj=null;
			pd.dismiss();
			Bundle b=msg.getData();
			String status=null;
			String responseMessage = null;
			System.out.println(b.toString());
			try {
				responseObj=new JSONObject(b.getString("response"));
				status=responseObj.getString("response_type");
				responseMessage = responseObj.getString("response");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(status.equals("success"))
			{
				Toast.makeText(SignUpActivity.this, responseMessage, Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
				SignUpActivity.this.startActivity(intent);
				System.out.println("Destroying activity");
				SignUpActivity.this.finish();
			}
			else if(status.equals("Wrong"))
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
				builder.setMessage(responseMessage);
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
	
	public final static boolean isValidEmail(CharSequence target) {
	    if (target == null) {
	    	System.out.println("email null");
	        return false;
	    } else {
	        boolean valid =  android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	        if(valid){
	        	System.out.println("email valid");
	        }else {
	        	System.out.println("email invalid");
	        }
	        return valid;
	    }
	}

}
