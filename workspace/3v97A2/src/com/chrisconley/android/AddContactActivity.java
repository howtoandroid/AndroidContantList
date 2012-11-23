package com.chrisconley.android;

/********************************************************
 * CONTACTS MANAGER -3v97 Assignment 2
 ********************************************************
 *CHRIS CONLEY
 ********************************************************/


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddContactActivity extends MainActivity {

	private Button addContact;
    private EditText fText;
    private EditText lText;
    private EditText pText;
    private EditText eText;
    private EditText bText;
	public DatabaseHandler db = new DatabaseHandler(this);
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        //set the widgets to their xml counterparts
        fText = (EditText) findViewById(R.id.fnameText);
        lText = (EditText) findViewById(R.id.lnameText);
        pText = (EditText) findViewById(R.id.phoneText);
        eText = (EditText) findViewById(R.id.emailText);
        bText = (EditText) findViewById(R.id.bdayText);
        addContact = (Button) findViewById(R.id.addContact);
        // create the intent to send back to force an update of
        // the list view so the newly added contact is included
        final Intent i = getIntent();
        // on button click listener
        addContact.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
    			Contact c = new Contact();
    			c.setFirstName(fText.getText().toString());
    			c.setLastName(lText.getText().toString());
    			c.setEmail(eText.getText().toString());
    			c.setPhoneNumber(pText.getText().toString());
    			c.setBirthday(bText.getText().toString());
    			// add the new contact to the database
    			db.addContact(c);
    			// force the update to the list view
    			setResult(Activity.RESULT_OK,i);
    	        finish();
    		}
    	});
    }

    // menu is not used
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
