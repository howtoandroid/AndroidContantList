package com.chrisconley.android;
/********************************************************
 * CONTACTS MANAGER -3v97 Assignment 2
 ********************************************************
 *CHRIS CONLEY
 ********************************************************/

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class EditContactActivity extends MainActivity {

	

    private EditText fText;
    private EditText lText;
    private EditText pText;
    private EditText eText;
    private EditText bText;
	public DatabaseHandler db = new DatabaseHandler(this);
	public Contact editor;
	public Intent i;
    private final int edit_contact = 0;
    private final int delete_contact = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);
        
        // set the edit texts to the xml
        fText = (EditText) findViewById(R.id.fnameText2);
        lText = (EditText) findViewById(R.id.lnameText2);
        pText = (EditText) findViewById(R.id.phoneText2);
        eText = (EditText) findViewById(R.id.emailText2);
        bText = (EditText) findViewById(R.id.bdayText2);
        
        // get the bundled data from the intent
        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id");
        
        ArrayList<Contact> c = db.getAllContacts();
        editor = c.get(id);
        // set the edit texts values to the Contacts data
        fText.setText(c.get(id).firstName);
        lText.setText(editor.lastName.toString());
        pText.setText(editor.phoneNumber);
        bText.setText(editor.birthday);
        eText.setText(editor.email);
    }


    // menu button creates, creates a button for save and delete contact
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0, edit_contact, 0, "Save Changes");
    	menu.add(0, delete_contact, 0, "Delete Contact");
        return true;
    }
    
    // on menu item click it starts the appropriate function for the click
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
    	case edit_contact:
    		editContact();
            return true;
    	case delete_contact:
    		deleteContact();
    		return true;
        }

        
        return false;
    }
    
    // set the new editTexts values to the current contact and pass
    // it in to updateContact so that the values can be changed in the database
    public void editContact(){
    	Contact c = editor;
 		c.setFirstName(fText.getText().toString());
 		c.setLastName(lText.getText().toString());
 		c.setEmail(eText.getText().toString());
 		c.setBirthday(bText.getText().toString());
 		c.setPhoneNumber(pText.getText().toString());
 		// call updateContact
 		db.updateContact(c,editor.phoneNumber);
 		// get result from intent so the list view can be updated
 		setResult(Activity.RESULT_OK,i);
 	    finish();
    }
    
    // delete the current contact
    public void deleteContact(){
    	db.deleteContact(editor);
			setResult(Activity.RESULT_OK,i);
	        finish();
    }
    
   
    
}
