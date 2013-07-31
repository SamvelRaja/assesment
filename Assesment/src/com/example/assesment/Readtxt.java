package com.example.assesment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Readtxt extends Activity {
	  final int ACTIVITY_CHOOSE_FILE = 1;
	  String filePath;
	  public Parsetype ps=null;
	  public String aBuffer = "";
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.readtxtxml);

	    Button btn = (Button) this.findViewById(R.id.browse);
	  
	    btn.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View v) {
	        Intent chooseFile;
	        Intent intent;
	        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
	        chooseFile.setType("file/text");
	        intent = Intent.createChooser(chooseFile, "Choose a file");
	        startActivityForResult(intent, ACTIVITY_CHOOSE_FILE);
	      }
	    });
	   
	  }

	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    switch(requestCode) {
	      case ACTIVITY_CHOOSE_FILE: {
	        if (resultCode == RESULT_OK){
	          Uri uri = data.getData();
	          filePath = uri.getPath();
	          TextView path=(TextView)findViewById(R.id.data);
	          path.setText(filePath);
	         
	          try {
	          File myFile = new File(filePath);
	            FileInputStream fIn = new FileInputStream(myFile);
	            BufferedReader myReader = new BufferedReader(
	                    new InputStreamReader(fIn));
	            String aDataRow = "";
	            String aBuffer = "";
	            String type="";
	            int flag=1;
	            while ((aDataRow = myReader.readLine()) != null) {
	            	if(flag==1)
	            	{
	            		type+=aDataRow + "\n";
	            		flag=0;
	            	}
	            	else{
	                aBuffer += aDataRow;
	            	}
	               
	            }
	            path.setText(aBuffer);
	            
	            myReader.close();
	          Toast.makeText(getBaseContext(),
	                    "Done reading SD 'mysdfile.txt'",
	                    Toast.LENGTH_SHORT).show();
	          Database db=new Database(Readtxt.this);
	          db.open();
	          db.loadquestions(aBuffer);
	          db.loadtype(type);
	          Log.d("database","ended");
	          db.close();
	    
	          Intent i = new Intent(getBaseContext(), Parsetype.class);  
	          i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	          startActivity(i);
	          finish();
	          
	            
	        } catch (Exception e) {
	            Toast.makeText(getBaseContext(), e.getMessage(),
	                    Toast.LENGTH_SHORT).show();
	        }
	        }
	      
	      }
	    }
	} 
}