package com.chrisconley.android;

/********************************************************
 * CONTACTS MANAGER -3v97 Assignment 2
 ********************************************************
 *CHRIS CONLEY
  ********************************************************/


public class Contact {
	
	String firstName;
	String lastName;
	String phoneNumber;
	String birthday;
	String email;
	int id;

	
	//constructor
	public Contact(){}
	
	//constructor
	public Contact(String fname, String lname, String phone, String bday, String email, int id){
		this.firstName=fname;
		this.lastName=lname;
		this.phoneNumber=phone;
		this.birthday=bday;
		this.email=email;
		this.id=id;
	}
	
	//getters
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getBirthday(){
		return this.birthday;
	}
	
	public int getID(){
		return this.id;
	}
	
	//setters
	public void setFirstName(String f){
		this.firstName = f;
	}
	
	public void setLastName(String l){
		this.lastName = l;
	}
	
	public void setPhoneNumber(String p){
		this.phoneNumber = p;
	}
	
	public void setEmail(String e){
		this.email = e;
	}
	
	public void setBirthday(String b){
		this.birthday = b;
	}
	
	public void setID(int i){
		this.id = i;
	}

	// to string
	@Override
    public String toString() {
		return this.firstName + " " + this.lastName;
	}
}
