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
import android.widget.TextView;

public class Parsetype extends Activity {
	public Readtxt td=null;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.parse);
	    Button btn = (Button) this.findViewById(R.id.browse);
	    parsing();
	    btn.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View v) {
	        
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
         TextView parsedtxt=(TextView)findViewById(R.id.data);
         parsedtxt.setText(data);
         String words[]=data.split(";");
         Log.d("length","wrds"+words.length);
         List wordslist=Arrays.asList(words);
         Collections.shuffle(wordslist);
         words=(String[]) wordslist.toArray();
         for(int i=0;i<words.length;i++)
         {
        	 Log.d("words",words[i]);
         }
		 
		
	 }
}
