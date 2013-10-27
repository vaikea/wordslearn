package ru.vaikea.wordslearn;

import org.json.JSONException;
import org.json.JSONObject;

public class WordObject {
	public String word;
	public String transcription;
	public String translation;
	public WordObject(JSONObject go){
		try {
			word = go.getString("word");
			translation =  go.getString("translation");
			transcription =  go.getString("transcription");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public WordObject(String w, String trs, String tra){
		word = w;
		translation =  tra;
		transcription =  trs;		
	}
}
