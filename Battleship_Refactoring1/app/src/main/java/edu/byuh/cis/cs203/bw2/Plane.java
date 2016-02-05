package edu.byuh.cis.cs203.bw2;

import android.graphics.Canvas;
public class Plane extends Enemy {

//	public Plane(Enemy_Size size, Direction.height height) {
    public Plane(Enemy_Size size){
        super();
        setSizeRandom();
//        this.height = height;
        setHeightRandom();
        setRandomDirection();
        setLocation(0, startHeight());
        if (direction == Enemy_Direction.LEFT_RIGHT) {
            switch (size) {
                case SMALL:
                    setImage(R.drawable.little_airplane);
//                    image = GameView.myBitmapLoader(R.drawable.little_airplane);
                    break;
                case MEDIUM:
                    setImage(R.drawable.medium_airplane);
//                    image = GameView.myBitmapLoader(R.drawable.medium_airplane);
                    break;
                case LARGE:
                default:
                    setImage(R.drawable.big_airplane);
//                    image = GameView.myBitmapLoader(R.drawable.big_airplane);
                    break;
            }
        } else {
            switch (size) {
                case SMALL:
                    setImage(R.drawable.little_airplane_flip);
//                    image = GameView.myBitmapLoader(R.drawable.little_airplane_flip);
                    break;
                case MEDIUM:
                    setImage(R.drawable.medium_airplane_flip);
//                    image = GameView.myBitmapLoader(R.drawable.medium_airplane_flip);
                    break;
                case LARGE:
                default:
                    setImage(R.drawable.big_airplane_flip);
//                    image = GameView.myBitmapLoader(R.drawable.big_airplane_flip);
                    break;
            }
        }

        setPointValue(getEnemyPointValue());
        scaleThyself((int)(GameView.getViewWidth()), (int)(GameView.getViewHeight()));
    } //end of constructor

	@Override
	protected float relativeWidth(){
		switch (size) {
		case SMALL:
			return 0.05f;
		case MEDIUM:
			return 0.083f;
		case LARGE:
			default:
			return 0.1f;
		} 
	}

	@Override
	protected float relativeHeight(){
		switch (size) {
		case SMALL:
			return 0.05f;
		case MEDIUM:
			return 0.067f;
		case LARGE:
			default:
			return 0.067f;
		} 
	}

    @Override
    public float startHeight(){
        switch (height){
            case LOW:
                return GameView.getViewHeight()*.3f;
            case MIDDLE:
                return GameView.getViewHeight()*.2f;
            case HIGH:
                return GameView.getViewHeight()*.1f;
            default:
                return GameView.getViewHeight()*.2f;
        }
    }

    @Override
    public int explodingImage(){
        return R.drawable.airplane_explosion;
    }
    @Override
    public int getEnemyPointValue(){
        switch (size){
            case SMALL:
                return 75;
            case MEDIUM:
                return 20;
            case LARGE:
                return 15;
            default: return 15;
        }
    }
} //end of class
