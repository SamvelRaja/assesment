package com.example.assesment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class Match extends Activity{

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    ArrayList<String> data=new ArrayList<String>();
	    Bundle extras = getIntent().getExtras();
	    String lesson = null,type = null;
	    if (extras != null) {
	       lesson = extras.getString("arg1");
	       type=extras.getString("arg2");
	    }
	    maindatabase mdb=new maindatabase(Match.this);
        try {
			mdb.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      //String datastr=mdb.extracttype(lesson,type); 
        String datastr=mdb.getdata(lesson,type);
       Log.d("database",datastr);
        mdb.close();
        String words[]=datastr.split(";");
        Log.d("length","wrds"+words.length);
        for(int i=0;i<words.length;i++)
        {
       	 Log.d("words",words[i]);
        }
       List data1=Arrays.asList(words);
       Collections.shuffle(data1);
        words=(String[]) data1.toArray();
        for(int i=0;i<words.length;i++)
        {
       	 Log.d("words",words[i]);
       	data.add(words[i]);
        }
	    setContentView(R.layout.match);
	   GridView gridView = (GridView) findViewById(R.id.matchview);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item, data);
	    gridView.setAdapter(adapter);
	    
	}
	
}
