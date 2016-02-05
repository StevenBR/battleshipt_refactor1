package edu.byuh.cis.cs203.bw2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.media.MediaPlayer;

import java.util.ArrayList;

/**
 * Created by steve on 1/12/2016.
 */
public class DepthCharge extends Sprite {
    private MediaPlayer dCSound;
    int chargeCounter = 0;

    public DepthCharge (float BSCenterX, float BSCenterY, MediaPlayer mp){
//        super();
        image = GameView.myBitmapLoader(R.drawable.depth_charge);
//        velocity = new PointF(0, GameView.getViewHeight()/200);
        setVelocity(0, GameView.getViewHeight()/200);
        scaleThyself((int)GameView.getViewWidth(), (int)GameView.getViewHeight());
        setLocation(BSCenterX, BSCenterY/2);
        dCSound = mp;
    }

    @Override
    public void move(float viewWidth, float viewHeight){
        bounds.offset(this.velocity.x, this.velocity.y);
        //everytime this depthCharge moves it adds to counter, then plays sound at intervals
        chargeCounter++;
        if(chargeCounter == 10){
            dCSound.start();
            chargeCounter = 0;
        }
    }

    @Override
    protected float relativeWidth() {
        return .02f;
    }

    @Override
    protected float relativeHeight() {
        return .02f;
    }

    @Override
    public void destroy(ArrayList<Sprite> removeList){ removeList.add(this); }

    @Override
    public float startHeight() {
        return 0;
    }

}


/* you could make a superDepthCharge that goes through a certain number of enemies. */
/* make enemies drop little bombs, varrying explosive radiuses */
/*touch and hold to drag the ship around to avoid stuff or to get better shooting position */