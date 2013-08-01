package com.example.assesment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class start extends Activity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.startxml);
	    Button teach = (Button) this.findViewById(R.id.teacher);
	    Button stud= (Button) this.findViewById(R.id.student);
	    teach.setOnClickListener(new OnClickListener() {
		     @Override
		      public void onClick(View v) {
		    	  Intent i = new Intent(getBaseContext(), Readtxt.class);
		    	  startActivity(i);
		    	  finish();
		    	  }
		    });
	    stud.setOnClickListener(new OnClickListener() {
		      @Override
		      public void onClick(View v) {
		    	  Intent i = new Intent(getBaseContext(), studentmain.class);
		    	  startActivity(i);
		    	  finish();
		    	  }
		    });
	}
}
