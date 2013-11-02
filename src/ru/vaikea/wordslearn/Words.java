package ru.vaikea.wordslearn;

import java.util.ArrayList;

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
				JSONArray trans = (JSONArray) jo.get("translation");
				
				ContentValues cv = new ContentValues();
				cv.put("word", jo.getString("word"));
				cv.put("transcription", jo.getString("transcription"));

				Cursor c = wdb.query(WordsDBHelper.TB_W_NAME, new String[]{"id"}, "word = ?", new String[]{jo.getString("word")}, null, null, null);
				
				if(c.getCount()==0){
					wdb.insert(WordsDBHelper.TB_W_NAME, null, cv);
					c = wdb.query(WordsDBHelper.TB_W_NAME, new String[]{"id"}, "word = ?", new String[]{jo.getString("word")}, null, null, null);
					c.moveToFirst();
				} else {
					c.moveToFirst();
					wdb.update(WordsDBHelper.TB_W_NAME, cv, "id = "+c.getInt(0), null);
				}
				
				int wid = c.getInt(0);
				wdb.execSQL("delete from "+WordsDBHelper.TB_TR_NAME+" where word_id = "+wid);
				
				for(int j=0;j<trans.length();j++){
					cv = new ContentValues();
					cv.put("word_id", wid);
					cv.put("translation", (String) trans.get(j));
					wdb.insert(WordsDBHelper.TB_TR_NAME, null, cv);
				}
				
				
				//cv.put("translation", jo.getString("translation"));
								
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
		Cursor c = wdb.query(WordsDBHelper.TB_W_NAME, new String[]{"id, word","transcription"}, null, null, null, null, "Random() LIMIT 1");
		if(c.getCount()==0){
			return null;
		} else {
			c.moveToFirst();
			Cursor c2 = wdb.query(WordsDBHelper.TB_TR_NAME,new String[]{"translation"},"word_id = " + c.getInt(0),null,null,null,null);
			ArrayList<String> translations = new ArrayList<String>();
			if(c2.getCount()!=0){
				c2.moveToFirst();
				while(true){
					translations.add(c2.getString(0));
					if(c2.isLast())
						break;
					c2.moveToNext();
				}
			}
			WordObject wo = new WordObject(c.getString(1),c.getString(2),translations);
			return wo;
		}
	}
	
}




