package ru.vaikea.wordslearn;

import ru.vaikea.wordslearn.R;
import ru.vaikea.wordslearn.Words;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class LearnActivity extends Activity {

	void setTextInTV(int tvId,String text){
		TextView tv = (TextView) findViewById(tvId);
		Typeface fn = Typeface.createFromAsset(getAssets(), "DejaVuSans.ttf");
		tv.setTypeface(fn);
		tv.setText(text);
	}
	
	void showNextRndWord(){
        WordObject wo = Words.getRndWord(this);
        setTextInTV(R.id.tvWord, wo.word);
        setTextInTV(R.id.tvTranscript, wo.transcription);
        setTextInTV(R.id.tvTranslation, wo.translation);		
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        
        AdditionalMethods.AddSlMenu(this);
        
        showNextRndWord();
        View v = (View) findViewById(R.id.avlearn);
        v.setOnTouchListener(new OnSwipeTouchListener(){
        	public void onSwipeLeft() {
                showNextRndWord();
            }
        });
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.learn, menu);
        return true;
    }
    
}
