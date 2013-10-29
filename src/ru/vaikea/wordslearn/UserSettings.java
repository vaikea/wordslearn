package ru.vaikea.wordslearn;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import ru.vaikea.wordslearn.R;

public class UserSettings extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		AdditionalMethods.AddSlMenu(this);
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
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
