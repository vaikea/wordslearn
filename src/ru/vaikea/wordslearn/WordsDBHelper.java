package ru.vaikea.wordslearn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WordsDBHelper extends SQLiteOpenHelper {

	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "words";
	private static final String TB_W_NAME = "words";	
	private static final String TB_R_NAME = "results";
	private static final String CREATE_W_CMD = "Create table "+TB_W_NAME+" (id integer primary key autoincrement,word text, translation text)";
	private static final String CREATE_R_CMD = "Create table "+TB_R_NAME+" (id integer primary key autoincrement,word_id integer, racount integer, wacount integer)";
	
	public WordsDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(CREATE_W_CMD);	
		arg0.execSQL(CREATE_R_CMD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

}
