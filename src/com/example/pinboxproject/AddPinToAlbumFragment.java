package com.example.pinboxproject;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.pinboxproject.adapters.AddPinToAlbumAdapter;
import com.example.pinboxproject.apputils.MyPrePopulatedDBHelper;
import com.example.pinboxproject.entity.Pin;
import com.example.pinboxproject.entity.Settings;

public class AddPinToAlbumFragment extends DialogFragment{
	
	ListView pinListView;
	Button buttonAddPin;
	Activity activity;
	AddPinToAlbumAdapter pinsAdapter;
	//ArrayList<IncidentComment> comments;
	//CommentHandler commentHandler;
	
	ArrayList<Pin> pins;
	
	public AddPinToAlbumFragment(Activity activity){
		this.activity = activity;
		
		MyPrePopulatedDBHelper dbHelper = new MyPrePopulatedDBHelper(activity, "tik");
		pins = dbHelper.getUserPins(Settings.loggedUser.getId());
		//this.comments=c;
	}
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    View view = inflater.inflate(R.layout.add_pin_in_album_dialog_layoit, null);
	    builder.setView(view).setTitle("Add pins to Album");
	    
	    
	    buttonAddPin = (Button)view.findViewById(R.id.add_pin_to_album_add);
	    buttonAddPin.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//addComment();
			}
		});
	    
	    pinListView = (ListView)view.findViewById(R.id.add_pin_to_album_list);
	    pinsAdapter = new AddPinToAlbumAdapter(activity, pins);
	    pinListView.setAdapter(pinsAdapter);
	    
	    // Add action buttons    
	    return builder.create();
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		/*if(activity instanceof CommentHandler){
			commentHandler = (CommentHandler)activity;
		}*/
	}
	
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		//commentHandler = null;
		
	}
	
	/*public static interface CommentHandler{
		
		public void getAddedComment(Comment comment);
	}
	
	private void addComment(){
		String comment = textDescription.getText().toString();
		//System.out.println("Comment in fragment: " + comment);
		Comment commentObj = new Comment();
		commentObj.id = SessionInfo.userID;
		commentObj.comment = comment;
		commentHandler.getAddedComment(commentObj);
		this.dismiss();
	}
	
	public static class Comment{
		String id;
		String comment;
	}*/
}
