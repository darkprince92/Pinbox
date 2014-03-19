package com.example.pinboxproject;

import java.util.ArrayList;

import com.example.pinboxproject.adapters.CommentListAdapter;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CommentDialogFragment extends DialogFragment{
	
	ListView commentListView;
	Button addComment;
	EditText textDescription;
	Activity activity;
	CommentListAdapter commentAdapter;
	//ArrayList<IncidentComment> comments;
	//CommentHandler commentHandler;
	
	public CommentDialogFragment(Activity activity){
		this.activity = activity;
		//this.comments=c;
	}
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    View view = inflater.inflate(R.layout.comment_dialog_layout, null);
	    builder.setView(view).setTitle("Comments");
	    
	    textDescription = (EditText)view.findViewById(R.id.editText_comment);
	    
	    addComment = (Button)view.findViewById(R.id.button_add_comment);
	    addComment.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//addComment();
			}
		});
	    
	    commentListView = (ListView)view.findViewById(R.id.listView_comments);
	    commentAdapter = new CommentListAdapter(activity);
	    commentListView.setAdapter(commentAdapter);
	    commentListView.setClickable(false);
	    
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
	}
*/		
}
