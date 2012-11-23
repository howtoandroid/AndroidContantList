package com.chrisconley.android;
/********************************************************
 * CONTACTS MANAGER -3v97 Assignment 2
 ********************************************************
 *CHRIS CONLEY
 ********************************************************/

import java.util.ArrayList;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;


public class DatabaseHandler extends SQLiteOpenHelper {
	
	public DatabaseHandler (Context context){
		super(context,dbName,null,dbVersion);
	}
	
	//database name
	private static final String dbName = "3v97Contacts3";
	//table name
	private static final String tableName = "Contacts";
	//version number
	private static final int dbVersion = 1;
	//column names
	private static final String keyFirstName = "first";
	private static final String keyLastName = "last";
	// the phone number is the primary key since no two people
	// can have the same number
	private static final String keyPhoneNumber = "phone";
	private static final String keyEmail = "email";
	private static final String keyBirthday = "bday";
	private static final String keyID = "id";
	
	

	
	//create the table
	@Override
	public void onCreate(SQLiteDatabase db){
		// construct the table with these columns
		String contactsTable = "CREATE TABLE " + tableName + "(" + keyID + "INTEGER PRIMARY KEY," 
				+ keyFirstName + " Text," + keyLastName + " Text," + keyPhoneNumber + " Text," 
				+ keyEmail + " Text," + keyBirthday + " Text" + ")";
		db.execSQL(contactsTable);
	}
	
	// not used but needed by the dbhandler
	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " + tableName);
		onCreate(db);
	}
	
	// used to add a contact to the database
	void addContact(Contact c){
		SQLiteDatabase db = this.getWritableDatabase();
		// an object used to hold values that ascertain to the columns in the database
		ContentValues cv = new ContentValues();
		cv.put(keyFirstName, c.getFirstName());
		cv.put(keyLastName, c.getLastName());
		cv.put(keyPhoneNumber, c.getPhoneNumber());
		cv.put(keyEmail, c.getEmail());
		cv.put(keyBirthday, c.getBirthday());
		// insert the contentValues object into the database
		db.insert(tableName,null,cv);
		db.close();
	}
	

	
	// return an ArrayList of contacts by gathering all contact information
	// from the database
	public ArrayList<Contact> getAllContacts(){
		// used to hold the contacts, will be returned
		ArrayList<Contact> list = new ArrayList<Contact>();
		// select the whole database
		String query = "SELECT * FROM "+ tableName;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor=db.rawQuery(query, null);
		// if its not empty
		if (cursor.moveToFirst()){
			do {
				// turn each row into a Contact and add it to the list
				Contact contact = new Contact();
				contact.setID(cursor.getInt(0));
				contact.setFirstName(cursor.getString(1));
				contact.setLastName(cursor.getString(2));
				contact.setPhoneNumber(cursor.getString(3));
				contact.setEmail(cursor.getString(4));
				contact.setBirthday(cursor.getString(5));
				list.add(contact);
			}while (cursor.moveToNext());
		}
		return list;
	}
	
	// delete the Contact c
	public void deleteContact(Contact c){
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete(tableName, keyPhoneNumber +"="+ c.phoneNumber,null);
		db.close();
	}
	
	// update the contact in the database by applying the data from the
	// various textfields to match its new values
	public int updateContact(Contact c, String row){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(keyFirstName, c.getFirstName());
		cv.put(keyLastName, c.getLastName());
		cv.put(keyPhoneNumber, c.getPhoneNumber());
		cv.put(keyEmail, c.getEmail());
		cv.put(keyBirthday, c.getBirthday());
	
        return db.update(tableName, cv, keyPhoneNumber +"="+ c.phoneNumber, null);
	}
	
	

	


			
}
