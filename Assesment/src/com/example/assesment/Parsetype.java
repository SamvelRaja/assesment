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
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Parsetype extends Activity {
	public Readtxt td=null;
	TextView typetv,lessontv;
	ArrayList<HashMap<String, String>> datalist;
	ArrayList<String> questions=new ArrayList<String>();
	ArrayList<String> answers=new ArrayList<String>();
	
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.parse);
	    Button btn = (Button) this.findViewById(R.id.load);
	    lessontv=(TextView)findViewById(R.id.lesson);
	    typetv=(TextView)findViewById(R.id.type);
	    datalist = new ArrayList<HashMap<String, String>>();
	    parsing();
	    btn.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View v) {
	  	
	    	  Database db=new Database(Parsetype.this);
	          try {
	 			db.open();
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	        String type=db.gettype(); 
	        String words[]=type.split(";");
	        String data=db.getdata();
	        
	        db.close();
	          
	          savedata(words[1],words[0],data);
	    	  Intent i = new Intent(getBaseContext(), Match.class);  
	    	  i.putExtra("arg1",words[1]);
	    	  i.putExtra("arg2",words[0]);
	          startActivity(i);
	      
	      }

		private void savedata(String lesson,String type, String data) {
			// TODO Auto-generated method stub
			maindatabase mdb=new maindatabase(Parsetype.this);
			try {
				mdb.open();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mdb.load(lesson, type, data);
			mdb.close();
		}
	    });
	}

	
	 public void parsing()
	 {	
		 
		 Database db=new Database(Parsetype.this);
         try {
			db.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       String type=db.gettype();  
       String data=db.getdata();  
         Log.d("database",data);
         db.close();
         String words[]=type.split(";");
         typetv.setText(findtype(words[0]));
         lessontv.setText(words[1]);
         String quest=null;
         String dataarray[]=data.split(";");
         for(int i=0;i<dataarray.length;i++)
         {
        	 HashMap<String, String> map = new HashMap<String, String>();
        	 if(i%2==0)
        	 {
        		 quest=dataarray[i];
        		 
        	 }
        	 else
        	 {
        		 map.put("Question", quest);
        		 map.put("Answer", dataarray[i]);
        		 datalist.add(map);
        	 }
        	 
             
         }
         
       ListView question = (ListView) findViewById(R.id.question);
 	   ListAdapter adapter = new SimpleAdapter(this,datalist,R.layout.parsedlist_item, new String[] { "Question","Answer"},new int[] { R.id.quest, R.id.ans });
 	   question.setAdapter(adapter);
 	 
	    
	     Log.d("length","wrds"+words.length);
         List wordslist=Arrays.asList(words);
         Collections.shuffle(wordslist);
         words=(String[]) wordslist.toArray();
         for(int i=0;i<words.length;i++)
         {
        	 Log.d("words",words[i]);
         }
		 
		
	 }


	private CharSequence findtype(String string) {
		// TODO Auto-generated method stub
		if(string.equalsIgnoreCase("MTF"))
		{
			return "Match";
		}
		return "Invalidtype";
	}
}
