package edu.byuh.cis.cs203.bw2;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Battleship extends Sprite {

	private static Battleship singleton;
	
	private Battleship() {
		super();
		image = GameView.myBitmapLoader(R.drawable.battleship);
	}

	@Override
	public void move(float viewWidth, float viewHeight) {
		//doesn't need to move yet
		//override later
	}

	public static Battleship getInstance() {
		if (singleton == null) {
			singleton = new Battleship();
		}
		return singleton;
	}

	public float getCenterX(){
		float x = bounds.centerX();
		return x;
	}
	public float getCenterY() {
		float y = bounds.centerY();
		return y;
	}
	@Override
	protected float relativeWidth() {
		return 0.4f;
	}

	@Override
	protected float relativeHeight() {
		return 0.125f;
	}

	@Override
	public void destroy(ArrayList<Sprite> removeList){}

    @Override
    public float startHeight() {
        return 0;
    }

    ;

}
