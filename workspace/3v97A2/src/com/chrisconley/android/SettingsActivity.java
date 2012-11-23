package com.chrisconley.android;
/********************************************************
 * CONTACTS MANAGER -3v97 Assignment 2
 ********************************************************
 *CHRIS CONLEY
 ********************************************************/

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;

public class SettingsActivity extends MainActivity {

	//widgets
	private CheckBox cb;
	private Spinner displaysp;
	private Spinner datesp;
	// name of the shared preference file
	public static final String PREFS_NAME = "prefs";
	public SharedPreferences.Editor editor;
	 
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // open shared preferences
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        // set the view
        setContentView(R.layout.activity_settings);
        // set up the widgets to their xml counterparts
        displaysp = (Spinner) findViewById(R.id.displaySpinner1);
        cb = (CheckBox) findViewById(R.id.checkBox1);
        datesp = (Spinner) findViewById(R.id.dateSpinner);
        // set up the editor for the preferences
        editor = preferences.edit();
        editor.putInt("Order",0);
        editor.putInt("Date", 0);
        editor.putInt("Display", 0);
        editor.commit();
        
        //array adapter used to populate the dateSpinner
        ArrayAdapter<CharSequence> dateAdapter = ArrayAdapter.createFromResource(
        		  this, R.array.DateArray, android.R.layout.simple_spinner_item );
        dateAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        datesp.setAdapter( dateAdapter );
        
        //array adapter used to populate the displaySpinner
        ArrayAdapter<CharSequence> displayAdapter = ArrayAdapter.createFromResource(
      		  this, R.array.displayArray, android.R.layout.simple_spinner_item );
        displayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        displaysp.setAdapter( displayAdapter );
        
        // on click listener for the checkbox
        cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				
				// update the shared preference depending on the checkbox's state
		        SharedPreferences.Editor editor = preferences.edit();
				 if(cb.isChecked()){
					 editor.putInt("Order",1);
	              }else{
	            	  editor.putInt("Order",0);
	             }
				 editor.commit();	
			}
        });

        // get selected item from the date spinner
        datesp.setOnItemSelectedListener(new OnItemSelectedListener(){
        	
        	// update the shared preference with the date spinner choice
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				SharedPreferences.Editor editor = preferences.edit();
				editor.putInt("Date", arg2);
				 editor.commit();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub			
			}
        });
        
        // get the selected item from the display spinner
        displaysp.setOnItemSelectedListener(new OnItemSelectedListener(){
        	
        	//update the shared preference with the display spinner
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				SharedPreferences.Editor editor = preferences.edit();
				editor.putInt("Display", arg2);
				editor.commit();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        });
             
    }

    // no menu in this activity, nothing to inflate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
