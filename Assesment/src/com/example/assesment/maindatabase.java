package com.example.assesment;

import java.sql.SQLException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class maindatabase {
	public static final String KEY_ROWID="id";
	public static final String KEY_ID="lesson";
	public static final String KEY_TYPE="type";
	public static final String KEY_DATA="data";
	
	
	public static final String DATABASE_NAME="assesment";
	public static final String DATABASE_TABLE="maintable";
	public static int DATABASE_VERSION=1;
	
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
					KEY_ID +" text not null, "+
				    KEY_TYPE +" text not null,  "+
			        KEY_DATA +" text not null);"
					);	
			  
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS '" + DATABASE_TABLE+"'");
			onCreate(db);
		}
		
	}
		public maindatabase(Context c)
		{
			ourContext=c;
		}
		
		public maindatabase open() throws SQLException{
			ourdb =new dbe(ourContext);
			ourDatabase=ourdb.getWritableDatabase();
			return this;
		}
		
		public void close()
		{
			ourdb.close(); 
		}
        
		
		public String getdata(String lesson,String type) {
		String[] columns=new String[]{KEY_ROWID ,KEY_ID,KEY_TYPE,KEY_DATA};
		String loctype="",locless="",result="";
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns,KEY_ROWID, null, null, null, null);
		Log.i("database retreive",result);
		c.moveToFirst();
		
		while(c.isAfterLast() == false)
		{
			locless=c.getString(1);
			loctype=c.getString(2);
			
			if(locless.equalsIgnoreCase(lesson)&&loctype.equalsIgnoreCase(type))
			result=result+c.getString(3);
			Log.i("database retreive",result);
			
			c.moveToNext();
			
		}
		c.close();
		return result;
			
		}

		public long load(String lesson,String type,String data) {
			// TODO Auto-generated method stub
			ContentValues cv=new ContentValues();
			cv.put(KEY_ID,lesson);
			cv.put(KEY_TYPE, type);
			cv.put(KEY_DATA, data);
			Log.d("databaseentry ID", lesson);
			Log.d("databaseentry TYPE",type);
			return ourDatabase.insert(DATABASE_TABLE, null, cv);
		}
		
		public int noofdata(){
			String[] columns=new String[]{KEY_ROWID ,KEY_ID,KEY_TYPE,KEY_DATA};
			
			Cursor c=ourDatabase.query(DATABASE_TABLE, columns,KEY_ROWID, null, null, null, null);
			
			c.moveToFirst();
			int count=0;
			while(c.isAfterLast() == false)
			{
				
				count++;
				c.moveToNext();
				
			}
			c.close();
			return count;	
		}
		
		
		public String getlesson(int i)
		{
            String[] columns=new String[]{KEY_ROWID ,KEY_ID,KEY_TYPE,KEY_DATA};
			
			Cursor c=ourDatabase.query(DATABASE_TABLE, columns,KEY_ROWID +"="+i, null, null, null, null);
			
			c.moveToFirst();
			
			if(c!=null)
			{
				return c.getString(1);
			}
			c.close();
			return null;
		}
		
		public String gettype(int i)
		{
            String[] columns=new String[]{KEY_ROWID ,KEY_ID,KEY_TYPE,KEY_DATA};
			
			Cursor c=ourDatabase.query(DATABASE_TABLE, columns,KEY_ROWID+"="+i, null, null, null, null);
			
			c.moveToFirst();
			
			if(c!=null)
			{
				return c.getString(2);
			}
			c.close();
			return null;
		}
}
