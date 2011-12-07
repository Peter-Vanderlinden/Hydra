package org.extremedevelopers.hydra.models;

import org.extremedevelopers.hydra.HydraEngine;
import org.extremedevelopers.hydra.HydraSurface;
import org.extremedevelopers.hydra.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

// surfaceview = 792 x 592 in virtualbox

public class Background {
	
	private static final String TAG = HydraEngine.class.getSimpleName();
	
	private Bitmap bottom_left;
	private Bitmap bottom_right;
	private Bitmap bottom;
	private Bitmap floor;
	private Bitmap left;
	private Bitmap right;
	private Bitmap top_left;
	private Bitmap top_right;
	private Bitmap top;
	
	public Background(HydraSurface surface) {
		bottom_left = BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_bottom_left);
		bottom_right = BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_bottom_right);
		bottom = BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_bottom);
		floor = BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_floor);
		left = BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_left);
		right = BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_right);
		top_left = BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_top_left);
		top_right = BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_top_right);
		top = BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_top);
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(top_left,0,0,null);
		for (int i=0; i<45; i++) {
			canvas.drawBitmap(top,16+i*16,0,null);
		}
		canvas.drawBitmap(top_right,736,0,null);
		for (int i=0; i<33; i++) {
			canvas.drawBitmap(left,0,16+i*16,null);
			for (int j=0; j<45; j++) {
				canvas.drawBitmap(floor,16+j*16,16+i*16,null);
			}
			canvas.drawBitmap(right,736,16+i*16,null);
		}
		canvas.drawBitmap(bottom_left,0,544,null);
		for (int i=0; i<45; i++) {
			canvas.drawBitmap(bottom,16+i*16,544,null);
		}
		canvas.drawBitmap(bottom_right,736,544,null);
	}

}
