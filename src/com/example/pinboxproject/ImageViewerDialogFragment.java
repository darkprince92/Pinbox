package com.example.pinboxproject;

import com.example.pinboxproject.apputils.ImageLoader;
import com.example.pinboxproject.entity.Settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class ImageViewerDialogFragment extends DialogFragment{
	
	String imagePath;
	ImageView iv;
	public ImageViewerDialogFragment(String path)
	{
		this.imagePath=path;
	}
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    View view = inflater.inflate(R.layout.image_viewer_layout, null);
	    
	    iv=(ImageView)view.findViewById(R.id.image_viewer_image);
	    ImageLoader imgLoader=new ImageLoader(getActivity().getApplicationContext());
		int loader=R.drawable.ic_launcher;
		imgLoader.DisplayImage(Settings.ROOT_URL+this.imagePath, loader, iv);
		
	    builder.setView(view).setTitle("Image");
	    	      
	    // Add action buttons
	    builder.setPositiveButton("Ok", null);
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
