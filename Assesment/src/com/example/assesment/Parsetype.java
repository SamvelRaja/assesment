package com.example.assesment;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Parsetype extends Activity {
	public Readtxt td=null;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.readtxtxml);
	    Button btn = (Button) this.findViewById(R.id.browse);
	    Button btn2 = (Button) this.findViewById(R.id.load);
	    btn.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View v) {
	        //parsing();
	    	  Intent i = new Intent(getBaseContext(), Match.class);  
	          i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	          startActivity(i);
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
       String data=db.check();  
        Log.d("database",data);
         db.close();
         String words[]=data.split(";");
         Log.d("length","wrds"+words.length);
         for(int i=0;i<words.length;i++)
         {
        	 Log.d("words",words[i]);
         }
         List wordslist=Arrays.asList(words);
         Collections.shuffle(wordslist);
         words=(String[]) wordslist.toArray();
         for(int i=0;i<words.length;i++)
         {
        	 Log.d("words",words[i]);
         }
		 
		
	 }
}
