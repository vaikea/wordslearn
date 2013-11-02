package ru.vaikea.wordslearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class WordsSync {
	
	public static final int WS_OK = 0;
	public static final int WS_NETERROR = 1;	
	
	public static final int doSync(final Context context){
		new Thread(){
			@Override
			public void run(){
				try{
			
					URL u = new URL(context.getResources().getString(R.string.strWebSyncServer));
					HttpURLConnection c = (HttpURLConnection) u.openConnection();
					c.setRequestMethod("GET");
					c.connect();
					InputStream in = (InputStream) c.getContent();
					BufferedReader  rd = new BufferedReader(new InputStreamReader(in));
					StringBuilder sb = new StringBuilder();
					String line;
					while((line = rd.readLine())!=null){
						sb.append(line);
					}
					
					JSONArray ja = new JSONArray(sb.toString());
					
					Words.importJSONarray(ja, context);
					
					
				} catch(MalformedURLException  e){
					e.printStackTrace();
				} catch (ProtocolException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}.start();
		return WS_OK;
	}

	public static final void delDB(Context context){
		SQLiteDatabase wdb = Words.getWordsDb(context);
		wdb.execSQL("delete from "+WordsDBHelper.TB_W_NAME);
		wdb.execSQL("delete from "+WordsDBHelper.TB_TR_NAME);		
	}
	
}
