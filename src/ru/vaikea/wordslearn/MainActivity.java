package ru.vaikea.wordslearn;

import com.example.test.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
 

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.sidemenu);
        menu.setBehindWidth(200);
        menu.setBackgroundColor(0xFF333333);
        
        final Button btn = (Button) findViewById(R.id.btnTest);
        btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WordsSync.doSync();				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
