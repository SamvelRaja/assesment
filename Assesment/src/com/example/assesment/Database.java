package com.example.assesment;

import java.sql.SQLException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database  {
	public static final String KEY_ROWID="id";
	public static final String KEY_ID="iden";
	
	
	public static final String DATABASE_NAME="location_dbck";
	public static final String DATABASE_TABLE="table2";
	public static int DATABASE_VERSION=4;
	
    private dbe ourdb;
    private final Context ourContext;
    private static SQLiteDatabase urDatabase;
    private SQLiteDatabase ourDatabase;
    
   
	
	public static class dbe extends SQLiteOpenHelper{

		public dbe(Context context )
		{
			super(context, DATABASE_NAME, null	,DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			  db.execSQL("DROP TABLE IF EXISTS '" + DATABASE_TABLE+"'");
			  db.execSQL("CREATE TABLE " + DATABASE_TABLE +" (" +
			        KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
					KEY_ID +" STRING);"
					);	
			  db.execSQL("insert into " + DATABASE_TABLE + "(" + KEY_ROWID + ","
		                + KEY_ID + ") values(1,'samvel')");
			  db.execSQL("insert into " + DATABASE_TABLE + "(" + KEY_ROWID + ","
		                + KEY_ID + ") values(2,'raja')");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS '" + DATABASE_TABLE+"'");
			onCreate(db);
		}
		
	}
		public Database(Context c)
		{
			ourContext=c;
		}
		
		public Database open() throws SQLException{
			ourdb =new dbe(ourContext);
			ourDatabase=ourdb.getWritableDatabase();
			return this;
		}
		
		public void close()
		{
			ourdb.close(); 
		}
        
		public static void crete()
		{
			ContentValues cv=new ContentValues();
			cv.put(KEY_ID, 1);
			urDatabase.insert(DATABASE_TABLE, null, cv);
		}
		public String getdata() {
		String[] columns=new String[]{KEY_ROWID ,KEY_ID};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=2", null, null, null, null);
		if(c!=null)
		{
			c.moveToFirst();
			String xi=c.getString(1);
			return xi;
		}
		return null;
			
		}

		public String gettype() {
			String[] columns=new String[]{KEY_ROWID ,KEY_ID};
			Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=1", null, null, null, null);
			if(c!=null)
			{
				c.moveToFirst();
				String xi=c.getString(1);
				return xi;
			}
			return null;
		}

		public long loadquestions(String i) {
			// TODO Auto-generated method stub
			ContentValues cv=new ContentValues();
			String xsr=Integer.toString(2);
			cv.put(KEY_ID, i);
			String where="id=?";
		    String[] value= {xsr};
			return ourDatabase.update(DATABASE_TABLE,cv,where,value);
		}
		
		public long loadtype(String i) {
			// TODO Auto-generated method stub
			ContentValues cv=new ContentValues();
			String xsr=Integer.toString(1);
			cv.put(KEY_ID, i);
			String where="id=?";
		    String[] value= {xsr};
			return ourDatabase.update(DATABASE_TABLE,cv,where,value);
		}

}
