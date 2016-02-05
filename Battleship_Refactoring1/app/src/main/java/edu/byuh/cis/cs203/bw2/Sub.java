package edu.byuh.cis.cs203.bw2;

public class Sub extends Enemy {

//	public Sub(Enemy_Size size, Direction.height height) {
    public Sub(Enemy_Size size){
		super();
//		this.size = size;
        setSizeRandom();
//		this.height = height;
        setHeightRandom();
        setLocation(0, startHeight());
		setRandomDirection();
		if (direction == Enemy_Direction.LEFT_RIGHT) {
			switch (size) {
				case SMALL:
					image = GameView.myBitmapLoader(R.drawable.little_submarine);
					break;
				case MEDIUM:
					image = GameView.myBitmapLoader(R.drawable.medium_submarine);
					break;
				case LARGE:
				default:
					image = GameView.myBitmapLoader(R.drawable.big_submarine);
					break;
			}
		}  else {
			switch (size) {
				case SMALL:
					image = GameView.myBitmapLoader(R.drawable.little_submarine_flip);
					break;
				case MEDIUM:
					image = GameView.myBitmapLoader(R.drawable.medium_submarine_flip);
					break;
				case LARGE:
				default:
					image = GameView.myBitmapLoader(R.drawable.big_submarine_flip);
					break;
			}
		}

//		velocity.x = (float)(Math.random()*9+1);
//		velocity.y = 0;
		scaleThyself((int) (GameView.getViewWidth()), (int) (GameView.getViewHeight()));
		setPointValue(getEnemyPointValue());
	}

	@Override
	protected float relativeWidth() {
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
	protected float relativeHeight() {
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
				return GameView.getViewHeight()*.7f;
			case MIDDLE:
				return GameView.getViewHeight()*.8f;
			case HIGH:
				return GameView.getViewHeight()*.9f;
			default:
				return GameView.getViewHeight()*.8f;
		}
	}

	@Override
    public int explodingImage(){
        return R.drawable.submarine_explosion;
	}

    @Override
    public int getEnemyPointValue(){
        switch (size){
            case SMALL:
                return 150;
            case MEDIUM:
                return 40;
            case LARGE:
                return 25;
            default: return 25;
        }
    }


}
