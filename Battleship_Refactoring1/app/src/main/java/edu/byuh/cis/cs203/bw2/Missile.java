package edu.byuh.cis.cs203.bw2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.MediaPlayer;

import java.util.ArrayList;
//keep this class a minimun outline of the object
/**
 * Created by steve on 1/13/2016.
 */
public class Missile extends Sprite {
    Direction direction;
//    Battleship battleship;
    float bsCenterX;
    float bsCenterY;
    private Paint p;

    public Missile(Direction dir, float BSCenterX, float BSCenterY, MediaPlayer mp, Paint paint){
//        super();
        //so the missle has can do its calculations below
        this.direction = dir;
        this.p = paint;
        bsCenterX = BSCenterX;
        bsCenterY = BSCenterY;
        //sets missile color & width
        p.setColor(Color.BLACK);
        p.setStrokeWidth(10.5f);
        scaleThyself((int) GameView.getViewWidth(), (int) GameView.getViewHeight());
        //checking direction and instantiating correct missile: LEFT_RIGHT, or RIGHT_LEFT
        if(direction == Direction.RIGHT_LEFT){
//            velocity = new PointF(-(GameView.getViewWidth()/30), -(GameView.getViewHeight()/20));
            setVelocity(-(GameView.getViewWidth()/25), -(GameView.getViewHeight()/15));
            setLocation(bsCenterX/1.39f, bsCenterY/1.11f);
            mp.start();
        } else {
//            velocity = new PointF(GameView.getViewWidth()/30, -(GameView.getViewHeight()/20));
            setVelocity(GameView.getViewWidth()/30, -(GameView.getViewHeight()/20));
            setLocation(bsCenterX*1.3f, bsCenterY/1.11f);
            mp.start();
        }
//        System.out.println("missle start is: " + bsCenterX/1.39f);
    }//end of missile constructor

    @Override
    public void move(float viewWidth, float viewHeight) {
        bounds.offset(this.velocity.x, this.velocity.y);
    }
    //had to override in order to draw line and differentiate line angle
    @Override
    public void draw(Canvas c){
        if(direction == Direction.RIGHT_LEFT) {
            c.drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom, p);
        }else if(direction == Direction.LEFT_RIGHT) {
            c.drawLine(bounds.left, bounds.bottom, bounds.right, bounds.top, p);
        } else {
            System.out.println("Direction is not set");
        }
    }

    @Override
    public void scaleThyself(int w, int h) {
		int newWidth = (int)(relativeWidth());
		int newHeight = (int)(relativeHeight());
		bounds.right = bounds.left + newWidth;
		bounds.bottom = bounds.top + newHeight;
	}

    @Override
    protected float relativeWidth() {
        //change to relative size later
        return 10;
    }

    @Override
    protected float relativeHeight() {
        //change to relative size later
        return 15;
    }

    @Override
    public void destroy(ArrayList<Sprite> removeList){ removeList.add(this); }

    @Override
    public float startHeight() {
        return 0;
    }

}
