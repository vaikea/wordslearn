package ru.vaikea.wordslearn;

import ru.vaikea.wordslearn.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
 

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdditionalMethods.AddSlMenu(this);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.menuSettings:
			Intent i = new Intent(this, UserSettings.class);	
			startActivity(i);
    	}
		return true;
    }
    
}
