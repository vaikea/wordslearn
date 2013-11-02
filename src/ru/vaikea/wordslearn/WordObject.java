package ru.vaikea.wordslearn;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WordObject {
	public String word;
	public String transcription;
	public ArrayList<String> translation;
	public WordObject(JSONObject go){
		try {
			word = go.getString("word");
			JSONArray trans =  go.getJSONArray("translation");
			for(int i=0;i<trans.length();i++){
				translation.add((String) trans.get(i));
			}
			transcription =  go.getString("transcription");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public WordObject(String w, String trs, ArrayList<String> tra){
		word = w;
		translation =  tra;
		transcription =  trs;		
	}
}
