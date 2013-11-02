package ru.vaikea.wordslearn;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Button;
import ru.vaikea.wordslearn.R;

public class UserSettings extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		setContentView(R.layout.pref_layout);
		AdditionalMethods.AddSlMenu(this);
		
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		
		Button b = (Button) findViewById(R.id.btnDelDB);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				WordsSync.delDB(UserSettings.this);
			}
		});
		
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		Boolean b = arg0.getBoolean(arg1, true);
		if(b){
			WordsSync.doSync(this);
		}
		SharedPreferences.Editor e = arg0.edit();
		e.putBoolean(arg1, false);
		e.commit();
		CheckBoxPreference c = (CheckBoxPreference) findPreference("chkSynchro");
		c.setChecked(false);
	}
	
}
