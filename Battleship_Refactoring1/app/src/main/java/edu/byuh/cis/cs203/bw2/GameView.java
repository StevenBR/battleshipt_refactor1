package edu.byuh.cis.cs203.bw2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public class GameView extends View {

	private Bitmap water;
	private Battleship battleship;
	private Plane plane1, plane2, plane3;
	private Sub sub1, sub2, sub3;
	private boolean firstTime;
	private Paint paint;
	private static ArrayList<Sprite> enemies;
    private static ArrayList<Sprite> activeWeapons;
    private static ArrayList<Sprite> deademies;
    private static ArrayList<Sprite> deadWeapons;
	private static int w, h;
    private static ArrayList<Sprite> depthCharges;
    private static ArrayList<Sprite> dcMissed;
    private static DepthCharge dCharge;
    private static Context c;
    private static Missile missile;
    private static ArrayList<Sprite> missiles;
    //can probably use the same deadObject array instead of a bunch of different move arrays
    private static int chargeCounter;
    private static int leftMCounter;
    private static int rightMCounter;
    private static MediaPlayer leftCannon;
    private MediaPlayer rightCannon;
    private MediaPlayer chargeSound;
	private static Resources res;
    private boolean chargeReady;
    private boolean leftMissileReady;
    private boolean rightMissileReady;
    private int gameScore;
    private int points;

    /**
	 * Constructor for our View subclass. Loads all the images
	 * @param context a reference to our main Activity class
	 */
	public GameView(Context context) {
		super(context);
		res = getResources();
        gameScore = 0;
		water = BitmapFactory.decodeResource(getResources(), R.drawable.water);
		battleship = Battleship.getInstance();

        //moved to enemySetUP
//		plane1 = new Plane(Enemy.Enemy_Size.LARGE, Direction.height.LOW);
//		plane2 = new Plane(Enemy.Enemy_Size.MEDIUM, Direction.height.MIDDLE);
//		plane3 = new Plane(Enemy.Enemy_Size.SMALL, Direction.height.HIGH);
//		sub1 = new Sub(Enemy.Enemy_Size.LARGE, Direction.height.HIGH);
//		sub2 = new Sub(Enemy.Enemy_Size.MEDIUM, Direction.height.MIDDLE);
//		sub3 = new Sub(Enemy.Enemy_Size.SMALL, Direction.height.LOW);

        rightCannon = MediaPlayer.create(getContext(), R.raw.right_gun);
        leftCannon = MediaPlayer.create(getContext(), R.raw.left_gun);
        chargeSound = MediaPlayer.create(getContext(), R.raw.depth_charge);
        chargeReady = true;
        leftMissileReady = true;
        rightMissileReady = true;

		firstTime = true;
		paint = new Paint();
        //ArraysLists
		enemies = new ArrayList<Sprite>();
        activeWeapons = new ArrayList<Sprite>();
        missiles = new ArrayList<Sprite>();
        depthCharges = new ArrayList<Sprite>();
        deademies = new ArrayList<Sprite>();
        deadWeapons = new ArrayList<Sprite>();
        c = getContext();
        chargeCounter = 0;
        leftMCounter = 0;
        rightMCounter = 0;
        points = 0;
        //create a timer class for these. start timer, reset, or something like that
    }//end of GameView Constructor

    public static Bitmap myBitmapLoader(int id) {
        return BitmapFactory.decodeResource(res, id);
    }

    public static float getViewWidth(){return w;}
    public static float getViewHeight(){return h;}

    public static void removeObjects(ArrayList<Sprite> activeList, ArrayList<Sprite> removeList){
        for(Sprite obj : removeList){
            activeList.remove(obj);
            System.out.println(activeList + " array size: " + activeList.size());
        }
        removeList.clear();
    }//end of removeObjects
    //works for all sprites(Any enemies, depthCharge, Missile, etc...)

    public void didCollide(ArrayList<Sprite> activeEnemies, ArrayList<Sprite> activeWeapons){
        for(Sprite enemy : activeEnemies){
            for(Sprite weapon : activeWeapons){
                if (enemy.bounds.intersect(weapon.bounds)) {
                    points =+ enemy.getPointValue();
                    enemy.destroy(deademies);
                    weapon.destroy(deadWeapons);
                }
            }
        }
//        return false;
    }

    public void isOutOfBounds(Sprite obj, ArrayList<Sprite> removeList){
        if (obj.bounds.bottom < 0 || obj.bounds.top > h || obj.bounds.left > w || obj.bounds.right < 0)
            removeList.add(obj);
    }

    public static double randomGen(){
        double randNum = Math.random();
        return randNum;
    }

	public void enemySetUp(Canvas c){
        paint.setTextSize(130f);
        int w = c.getWidth();
        int h = c.getHeight();
//        plane1 = new Plane(Enemy.Enemy_Size.LARGE, Direction.height.LOW);
//        plane2 = new Plane(Enemy.Enemy_Size.MEDIUM, Direction.height.MIDDLE);
//        plane3 = new Plane(Enemy.Enemy_Size.SMALL, Direction.height.HIGH);
//        sub1 = new Sub(Enemy.Enemy_Size.LARGE, Direction.height.HIGH);
//        sub2 = new Sub(Enemy.Enemy_Size.MEDIUM, Direction.height.MIDDLE);
//        sub3 = new Sub(Enemy.Enemy_Size.SMALL, Direction.height.LOW);

        plane1 = new Plane(Enemy.Enemy_Size.LARGE);
        plane2 = new Plane(Enemy.Enemy_Size.MEDIUM);
        plane3 = new Plane(Enemy.Enemy_Size.SMALL);
        sub1 = new Sub(Enemy.Enemy_Size.LARGE);
        sub2 = new Sub(Enemy.Enemy_Size.MEDIUM);
        sub3 = new Sub(Enemy.Enemy_Size.SMALL);

//        int randLocation = (int)(randomGen()*10);

//        switch (randLocation){
//            case 3:
//                plane1.setLocation(0, Direction.height.LOW);
//            case
//        }

        //create a method that takes an int, use that int in a for loop that is instantiating your plane and sub respectively


        // since I dont have to specify anything and am only using the w,h I called scaleThyself in the constructor
//		set sizes in when you instantiate now.
//		plane1.scaleThyself(w, h);
//		plane2.scaleThyself(w, h);
//		plane3.scaleThyself(w, h);
//		sub1.scaleThyself(w, h);
//		sub2.scaleThyself(w, h);
//		sub3.scaleThyself(w, h);

//		set positions   heights are constance so I move to appropriate classes.
        //can probably set when you instantiate, becuase you know it works now
        // also make instantiation height random, and if enemy.size < # then create a new random enemy and height
//		plane1.setLocation(w * 0.1f, h * 0.1f);
//		plane2.setLocation(w * 0.4f, h * 0.2f);
//		plane3.setLocation(w * 0.8f, h * 0.1f);
//		sub1.setLocation(w * 0.2f, h * 0.8f);
//		sub2.setLocation(w * 0.5f, h * 0.7f);
//		sub3.setLocation(w * 0.8f, h * 0.9f);

        //when you  create the reset these and random, you could make it so that you give it a number and it instantiates that many planes.
//adding to array for cleanliness ;)
		enemies.add(plane1);
        enemies.add(plane2);
        enemies.add(plane3);
        enemies.add(sub1);
        enemies.add(sub2);
        enemies.add(sub3);
	} //end of enemySetUp

	@Override
	public void onDraw(Canvas c) {

        c.drawColor(Color.WHITE);
        c.drawText("Score: " + Integer.toString(points), 50, h/2, paint);
        w = c.getWidth();
        h = c.getHeight();
        if (firstTime) {
            firstTime = false;

            water = Bitmap.createScaledBitmap(water, w/45, h/30, true);
            battleship.scaleThyself(w, h);
            battleship.setLocation((w - battleship.getWidth()) / 2f,
                    h / 2 - battleship.getHeight() + water.getHeight());
            enemySetUp(c);
            Time timer = new Time();
        }

        float waterX = 0;
        while (waterX < w) {
            c.drawBitmap(water, waterX, h/2, paint);
            waterX += water.getWidth();
        }
        battleship.draw(c);

        //will always have enemies so no need for if. (game can end if you hit certain amount of objects?)
        for(Sprite enemy : enemies){
            enemy.draw(c);
        }
        if (activeWeapons.size() > 0) {
            for (Sprite weapon : activeWeapons) {
                weapon.draw(c);
            }
        }
        if(missiles.size() > 0){
            for(Sprite m : missiles){
                m.draw(c);
            }
        }

//        String pointValue = Integer.toString(points);
	}//end of onDraw

	public class Time extends Handler{
		//each game piece extends sprite so you can call move();
		//use timer for this.
		public Time(){
			sendMessageDelayed(obtainMessage(), 50);
		}

		@Override
		public void handleMessage(Message m){
			//makes sure guns fires in intervals (check if they are ready)
            if(chargeCounter != 0)
                chargeCounter--;
             else
                chargeReady = true;
            if(leftMCounter != 0)
                leftMCounter--;
             else
                leftMissileReady = true;
            if(rightMCounter != 0)
                rightMCounter--;
             else
                rightMissileReady = true;

            for(Sprite enemy : enemies)
                enemy.move(getViewWidth(), getViewHeight());

            if(deadWeapons.size() > 0) {
                removeObjects(activeWeapons, deadWeapons);
//                removeObjects(missiles, deadWeapons);
            }
//            deadWeapons.clear();
            if(activeWeapons.size() > 0) {
                for (Sprite weapon : activeWeapons) {
                    weapon.move(getViewWidth(), getViewHeight());
                    isOutOfBounds(weapon, deadWeapons);
                }
            }

            didCollide(enemies, activeWeapons);
            if (deademies.size() > 0) {
                removeObjects(enemies, deademies);
            }

            invalidate(); //refresh/redo/resend/etc...
			sendMessageDelayed(obtainMessage(), 50);
		}
	} //end of Handler

    public boolean onTouchEvent(MotionEvent m){
        PointF touchLoc = new PointF(m.getX(), m.getY());
        if (m.getAction() == MotionEvent.ACTION_DOWN) {

            if(touchLoc.y > getViewHeight()/2){
//                if (counter%5 == 0 || counter == 0) {
                if(chargeReady == true) {
                    chargeCounter = 30;
                    chargeReady = false;
                    dCharge = new DepthCharge(battleship.getCenterX(), getViewHeight(), chargeSound);
                    activeWeapons.add(dCharge);
                }
            }
            if(touchLoc.y < getViewHeight()/2 && touchLoc.x < getViewWidth()/2) {
                if(leftMissileReady == true) {
                    leftMCounter = 7;
                    leftMissileReady = false;
                    missile = new Missile(Direction.RIGHT_LEFT,battleship.getCenterX(), battleship.getCenterY(), leftCannon, paint);
                    activeWeapons.add(missile);
                }
            }
            if(touchLoc.y < getViewHeight()/2 && touchLoc.x > getViewWidth()/2) {
                if(rightMissileReady == true) {
                    rightMCounter = 7;
                    rightMissileReady = false;
                    missile = new Missile(Direction.LEFT_RIGHT, battleship.getCenterX(), battleship.getCenterY(), rightCannon, paint);
                    activeWeapons.add(missile);
                }
            }
        }
        return true;
    }//end of onTouch
//
//    //should I pass missles and Planes, then charges and Subs to this?
//    public void collisionDetection() {
//        // see if missles intersect object
//
//        //if it does{
//        //enemyObject.explode(); here
//        //probably move removeObject here from he handler
//        //}
//    }

}//end of GameView
