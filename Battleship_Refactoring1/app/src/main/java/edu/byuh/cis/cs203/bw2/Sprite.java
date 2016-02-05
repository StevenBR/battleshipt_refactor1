package edu.byuh.cis.cs203.bw2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Message;

import java.util.ArrayList;

public abstract class Sprite {
	
	protected Bitmap image;
	protected RectF bounds;
	private Paint paint;
	protected PointF velocity;
	protected static float viewWidth, viewHeight; //so everything can reference the same gameView dimensions. implement later
	private int spritePoints;
	
	public Sprite() {
		bounds = new RectF();
		velocity = new PointF(0, 0);
//		System.out.println(velocity);
	}

	public abstract void move(float viewWidth, float viewHeight);
    //passing params so I know that the the view dimensions are used to move the object. set w/h so you don't have to use the gameview.getBlah method
    //can probably set with static viewWidth and viewHeight variables later;

    public void setImage(int imageId){
        image = GameView.myBitmapLoader(imageId);
    };

	public void setLocation(float x, float y) {
		bounds.offsetTo(x, y);
	}

    public void setVelocity(float x,float y){
        velocity.x = x;
        velocity.y = y;
    }
	
	public void draw(Canvas c) {
		c.drawBitmap(image, bounds.left,  bounds.top, paint);
	}

    //the static viewWidth(Height) could also be used here instead of passing the params.
    //that would make scaling automatic;
	public void scaleThyself(int w, int h) {
		int newWidth = (int)(w * relativeWidth());
		int newHeight = (int)(h * relativeHeight());
		image = Bitmap.createScaledBitmap(image,
				newWidth, newHeight, true);
		bounds.right = bounds.left + newWidth;
		bounds.bottom = bounds.top + newHeight;
	}

	public void setPointValue(int points){
		this.spritePoints = points;
	}
	public int getPointValue(){
		return this.spritePoints;
	}

	public float getWidth() {
		return bounds.width();
	}
	public float getHeight() {
		return bounds.height();
	}


	protected abstract float relativeWidth();
	protected abstract float relativeHeight();
	public abstract void destroy(ArrayList<Sprite> removeList);
    public abstract float startHeight();
//    public abstract float startWidth();
//    protected abstract boolean isOutOfBounds();
}
