package com.chrisconley.android;

/********************************************************
 * CONTACTS MANAGER -3v97 Assignment 2
 ********************************************************
* CHRIS CONLEY ********************************************************/


import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends Activity {

	 //database handler object
	 public DatabaseHandler db = new DatabaseHandler(this);
	 //used to hold the contact objects
	 public ArrayList<Contact> currentDB;
	 public ArrayList<Contact> display;
	 //widgets
	 private EditText searchBox;
	 private ListView lv;
	 public ArrayAdapter<Contact> ca;
	 // integer representations for the menu items  
	 private final int add_contact = 0;
	 private final int settings = 1;
	    
	    
	    
	    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //set the widgets to match their xml views
        lv = (ListView) findViewById(R.id.listView);
        searchBox = (EditText) findViewById(R.id.SearchText);
        updateList();	//populate the list view

    	//on click listener for the list view. When clicked it passes
        //the object to the editLauncher function. It then refreshes the
        // list view
    	lv.setOnItemClickListener(new OnItemClickListener() { 
    		public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    		{ editLauncher(position);
    		updateList();
    	}});
    	
    	//used to filter results in the list view
    	searchBox.addTextChangedListener(new TextWatcher() {
			 
		    @Override
		    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
		        // When user changes the text filter the listview for the charachters cs
		        MainActivity.this.ca.getFilter().filter(cs);
		    }
		 
		    @Override
		    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
		            int arg3) {
		       // TODO Auto-generated method stub
		    	}
		    @Override
		    public void afterTextChanged(Editable arg0) {
		        // TODO Auto-generated method stub
		    }
		});
    }
    
    
    // updates the list. Uses the database handler to query all the contacts in the 
    // database. It then attatches it to the ArrayAdapter and sets the list view to
    // display the adapter
    public void updateList(){
    	DatabaseHandler d = new DatabaseHandler(this);
    	currentDB = d.getAllContacts();
        ca = new ArrayAdapter<Contact>(this,android.R.layout.simple_list_item_1,currentDB);
       lv.setAdapter(ca);
    }


    // When the user returns to this activity from an intent it spawned this function
    // is activated and it updates the list view
    @Override 
    public void onActivityResult(int requestCode, int resultCode, Intent data) {     
      super.onActivityResult(requestCode, resultCode, data); 
      updateList(); 
    }

    
    // open the menu when it the hardware button is clicked
    // add the menu items to the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0, add_contact, 0, "Add Contact");
    	menu.add(0, settings, 0, "Settings");
        return true;
    }
    
    // When a menu item is pressed it gets the menu item's
    // id number calls the appropriate function
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
    	case add_contact:
    		launchAdder();
            return true;
        case settings:
        	settingsLauncher();
        	return true;
        }
        return false;
    }
    
    // function is called when the user presses 'Add Contact' in the munu
    // it spawns a new intent and starts it and waits for a result, so that
    // the onActivityResult function is activated when the intent is finished
    // so that the listview will update
    protected void launchAdder() {
        Intent i = new Intent(this, AddContactActivity.class);
        startActivityForResult(i, 0);

    }
    
    // function is called when the user presses a contact on the list view
    // it spawns a new intent and starts it and waits for a result, so that
    // the onActivityResult function is activated when the intent is finished
    // so that the listview will update
    protected void editLauncher(long id){
    	Intent i = new Intent(this, EditContactActivity.class);
    	i.putExtra("id", (int)id);
    	startActivityForResult(i,0);
    }
    
    // function is called when the user presses 'Settings' in the munu
    // it spawns a new intent and starts it and waits for a result, so that
    // the onActivityResult function is activated when the intent is finished
    // so that the listview will update
    protected void settingsLauncher(){
    	Intent i = new Intent(this, SettingsActivity.class);
    	startActivityForResult(i,0);
    }
    
    
    
}
