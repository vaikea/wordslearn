package ru.vaikea.wordslearn;

import ru.vaikea.wordslearn.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class AdditionalMethods {

	public static final SlidingMenu AddSlMenu(Activity context){
        SlidingMenu menu = new SlidingMenu(context);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(context, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.sidemenu);
        menu.setBehindWidth(200);
        menu.setBackgroundColor(0xFF333333);
        
        bindMenuButtons(context, menu);
        
        return menu;
	}
	
	public static final void bindMenuButtons(final Activity context, final SlidingMenu menu){
      
		final Button btn2 = (Button) context.findViewById(R.id.btnLearn);
        btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				menu.toggle();
				Intent i = new Intent(context, LearnActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				context.startActivity(i);
			}
		}); 
        
		final Button btn3 = (Button) context.findViewById(R.id.btnOptions);
        btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				menu.toggle();
				Intent i = new Intent(context, UserSettings.class);
				i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				context.startActivity(i);
			}
		});  
        
		final Button btn4 = (Button) context.findViewById(R.id.btnHome);
        btn4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				menu.toggle();
				Intent i = new Intent(context, MainActivity.class);	
				i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				context.startActivity(i);
			}
		});           
        
	}
	
}









