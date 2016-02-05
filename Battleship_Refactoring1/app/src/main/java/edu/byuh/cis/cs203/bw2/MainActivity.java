package edu.byuh.cis.cs203.bw2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private GameView gv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gv = new GameView(this);
		setContentView(gv);
	}


}
