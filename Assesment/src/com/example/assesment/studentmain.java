package com.example.assesment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class studentmain extends Activity {
	ArrayList<HashMap<String, String>> asseslist;
	ArrayList<String> lessons=new ArrayList<String>();
	ArrayList<String> type=new ArrayList<String>();
	int count=0;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    asseslist = new ArrayList<HashMap<String, String>>();
	    setContentView(R.layout.studentmain);
	    maindatabase mdb=new maindatabase(studentmain.this);
        try {
			mdb.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     count=mdb.noofdata();
	 for(int i=0;i<count;i++)
	 {
		     HashMap<String, String> map = new HashMap<String, String>();
    		 map.put("Lesson", mdb.getlesson(i+1));
    		 map.put("Type", mdb.gettype(i+1));
    		 asseslist.add(map);
	}
	   
       ListView question = (ListView) findViewById(R.id.assesments);
	   ListAdapter adapter = new SimpleAdapter(this,asseslist,R.layout.studentmainlist_item, new String[] { "Lesson","Type"},new int[] { R.id.less, R.id.typ });
	   question.setAdapter(adapter);
	   mdb.close();
	   
	   
       // on seleting single product
       // launching Edit Product Screen
       question.setOnItemClickListener(new OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent, View view,
                   int position, long id) {
               // getting values from selected ListItem
               String lesson = ((TextView) view.findViewById(R.id.less)).getText()
                       .toString();
               String type= ((TextView) view.findViewById(R.id.typ)).getText()
                       .toString();
               // Starting new intent
               Intent in = new Intent(getApplicationContext(),
                       Match.class);
               // sending pid to next activity
               in.putExtra("arg1", lesson);
               in.putExtra("arg2",type);

               // starting new activity and expecting some response back
               startActivity(in);
               finish();
           }
       });
	   
}
}
