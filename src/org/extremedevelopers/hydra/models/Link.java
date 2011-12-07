package org.extremedevelopers.hydra.models;

import org.extremedevelopers.hydra.HydraSurface;
import org.extremedevelopers.hydra.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Link {
	
	private Bitmap bitmap;
	private int x;
	private int y;
	
	public Link(HydraSurface surface) {
		bitmap = BitmapFactory.decodeResource(surface.getResources(), R.drawable.link_front_1);
		x = 80;
		y = 80;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap,x,y,null);
	}
	
	public void move(int down, int right) {
		y += down;
		x += right;
	}

}
