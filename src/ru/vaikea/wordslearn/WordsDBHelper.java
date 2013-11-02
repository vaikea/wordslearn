package ru.vaikea.wordslearn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WordsDBHelper extends SQLiteOpenHelper {

	private static final int DB_VERSION = 2;
	private static final String DB_NAME = "words";
	public static final String TB_W_NAME = "words";	
	public static final String TB_IrV_NAME = "irverbs";
	public static final String TB_TR_NAME = "translations";
	public static final String TB_R_NAME = "results";
	public static final String TB_IMPORT_NAME = "imports";
	
	private static final String CREATE_W_CMD = "Create table "
			+ TB_W_NAME
			+ " (id integer primary key autoincrement,word text, transcription text, isIrregular boolean)";
	
	private static final String CREATE_R_CMD = "Create table " + TB_R_NAME
			+ " (id integer primary key autoincrement,"
			+ "word_id integer, racount integer, wacount integer"
			+ ")";
	
	private static final String CREATE_TR_CMD = "Create table "
			+ TB_TR_NAME + "(id integer primary key autoincrement,"
			+ "word_id integer,translation text)";
	
	private static final String CREATE_I_CMD = "Create table " + TB_IMPORT_NAME + 
			"(importdate date,importfile text)";
	
	private static final String CREATE_IrV_CMD = "Create table " + TB_IrV_NAME + "" +
			"(id integer primary key autoincrement,word_id integer,irform text)";

	public WordsDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(CREATE_W_CMD);	
		arg0.execSQL(CREATE_R_CMD);
		arg0.execSQL(CREATE_TR_CMD);
		arg0.execSQL(CREATE_I_CMD);
		arg0.execSQL(CREATE_IrV_CMD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		if(arg1==1&&arg2==2){
			arg0.execSQL("drop table words");
			arg0.execSQL("drop table results");
			this.onCreate(arg0);
		}
	}

}
