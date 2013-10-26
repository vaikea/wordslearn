package ru.vaikea.wordslearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;

import android.R;
import android.util.Log;

public class WordsSync {
	
	public static final int WS_OK = 0;
	public static final int WS_NETERROR = 1;	
	
	public static final int doSync(){
		new Thread(){
			@Override
			public void run(){
				try{
					URL u = new URL("http://192.168.0.8/words.json");
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
}
