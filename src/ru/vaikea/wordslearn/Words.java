package ru.vaikea.wordslearn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Words {

	public static final int STATUS_OK = 0;
	
	public static final SQLiteDatabase getWordsDb(Context context){
		WordsDBHelper wdbh = new WordsDBHelper(context);
		return wdbh.getWritableDatabase();
	}
	
	public static final int importJSONarray(JSONArray ja,Context context){
		
		SQLiteDatabase wdb = getWordsDb(context);

		for(int i=0;i<ja.length();i++){
			try {
				JSONObject jo = ja.getJSONObject(i);
				ContentValues cv = new ContentValues();
				cv.put("word", jo.getString("word"));
				cv.put("translation", jo.getString("translation"));
				cv.put("transcription", jo.getString("transcription"));				
				Cursor c = wdb.query(WordsDBHelper.TB_W_NAME, null, "word = ?", new String[]{jo.getString("word")}, null, null, null);
				if(c.getCount()==0){
					wdb.insert(WordsDBHelper.TB_W_NAME, null, cv);
				} else {
					wdb.update(WordsDBHelper.TB_W_NAME, cv, "word = ?", new String[]{jo.getString("word")});
				}
			} catch (JSONException e) {
				wdb.close();
				e.printStackTrace();
			}
		}
		
		wdb.close();
		
		
		return STATUS_OK;
	}
	
	public static final WordObject getRndWord(Context context){
		SQLiteDatabase wdb = getWordsDb(context);
		Cursor c = wdb.query(WordsDBHelper.TB_W_NAME, new String[]{"word","translation","transcription"}, null, null, null, null, "Random() LIMIT 1");
		if(c.getCount()==0){
			return null;
		} else {
			c.moveToFirst();
			WordObject wo = new WordObject(c.getString(0),c.getString(2),c.getString(1));
			return wo;
		}
	}
	
}




