package ru.vaikea.wordslearn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Words {

	public static final int STATUS_OK = 0;
	
	public static final int importJSONarray(JSONArray ja,Context context){
		
		WordsDBHelper wdbh = new WordsDBHelper(context);
		SQLiteDatabase wdb = wdbh.getWritableDatabase();
		
		for(int i=0;i<ja.length();i++){
			try {
				JSONObject jo = ja.getJSONObject(i);
				ContentValues cv = new ContentValues();
				cv.put("word", jo.getString("word"));
				cv.put("translation", jo.getString("translation"));
				cv.put("transcription", jo.getString("transcription"));
				wdb.insert(WordsDBHelper.TB_W_NAME, null, cv);
				wdb.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		
		return STATUS_OK;
	}
	
}
