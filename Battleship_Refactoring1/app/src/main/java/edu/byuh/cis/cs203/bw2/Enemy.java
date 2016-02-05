package edu.byuh.cis.cs203.bw2;

import android.graphics.PointF;
import android.os.CountDownTimer;

import java.util.ArrayList;
//should create constructors with different parameter sets that include (but not limited to): viewW(H), Context, size
//change so that it sets locations to off the screen

//do I want to make this an abstract class?
public abstract class Enemy extends Sprite {
	protected Enemy_Direction direction;
	protected Enemy_Size size;
    protected Direction.height height;
    //should have getters and setters for your variables above

	public enum Enemy_Size{ SMALL, MEDIUM, LARGE; }
	protected enum Enemy_Direction{ LEFT_RIGHT, RIGHT_LEFT }
    protected abstract int explodingImage();
//    protected abstract float height();

	public Enemy() {
		super();
	} //end of constructor


    @Override
    public void setLocation(float w, float h){
        bounds.offsetTo(w, startHeight());
    }

    public abstract int getEnemyPointValue();

//    @Override
    public void setRandomDirection(){
        double rand = Math.random();
        //set directions 50% for either LEFT_RIGHT or RIGHT_LEFT
        if (rand < 0.5) {
            this.direction = Enemy_Direction.LEFT_RIGHT;
        } else {
            this.direction = Enemy_Direction.RIGHT_LEFT;
        }
    }

    public void setHeightRandom(){
        double rand =  GameView.randomGen();
        if(rand < .33){
            this.height = Direction.height.LOW;
        } else if(rand < .66){
            this.height = Direction.height.MIDDLE;
        } else{
            this.height = Direction.height.HIGH;
        }
    }

    public void setSizeRandom(){
        double rand =  GameView.randomGen();
        if(rand <= .33){
            this.size = Enemy_Size.SMALL ;
        } else if(rand <= .66){
            this.size = Enemy_Size.MEDIUM;
        } else {
            this.size = Enemy_Size.LARGE;
        }
    }

	public void randVelocity(){
//		float xv = (float)(Math.random()*-8-1);
//		velocity = new PointF(xv, 0);
        setVelocity((float)(Math.random()*-8-1), 0);
	}

	@Override
	public void move(float viewWidth, float viewHeight){
        if(bounds.right <= 0 ){
            setLocation(viewWidth, bounds.top);
        } else if(bounds.left > viewWidth){
            setLocation(0-bounds.width() , bounds.top);
        }

//		set random velocity if rand generates a number less than .1
        double rand = Math.random();
        if(rand < 0.1) {
            randVelocity();
        }
        //checks direction, then sets -velocity if RIGHT_LEFT
        if(direction != null) {
            if (direction == Enemy_Direction.RIGHT_LEFT) {
                bounds.offset(-velocity.x, 0);
            } else {
                bounds.offset(velocity.x, 0);
            }
        }
	}

// do I want to pass the image as a param?
    @Override
    public void destroy(final ArrayList<Sprite> removeList){
//        jk call here and set velocity
        new CountDownTimer(1005, 1000) {

            public void onTick(long millisUntilFinished) {
                setVelocity(0, 0);
                setImage(explodingImage());
            }

            public void onFinish() {
                removeList.add(Enemy.this);
            }
        }.start();
    }

//    public abstract int getPointValue();

    public void resetEnemy(){

    }

} //end of class
