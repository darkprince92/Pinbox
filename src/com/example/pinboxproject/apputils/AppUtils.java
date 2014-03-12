package com.example.pinboxproject.apputils;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;

public class AppUtils {
	
	public static Bitmap CreateThumbnail(Bitmap bitmap, int itemWidth){
		float ratio;			
		try{
			Bitmap imageThumb;
			if(bitmap.getWidth() > bitmap.getHeight()){
				ratio = (float)bitmap.getWidth()/(float)bitmap.getHeight();
				imageThumb = ThumbnailUtils.extractThumbnail(bitmap, 
						itemWidth, (int)((float)itemWidth/ratio));
			}
			else {
				ratio = (float)bitmap.getHeight()/(float)bitmap.getWidth();
				imageThumb = ThumbnailUtils.extractThumbnail(bitmap, 
						itemWidth, (int)(ratio*(float)itemWidth));
			}
			return imageThumb;
		}catch (Exception e) {
				// TODO: handle exception
				System.out.println("thumb not created: " + e);
		}
		return null;
	}
	
}
