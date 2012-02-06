package edu.sasq.Chickenkiller.android;

import java.util.ArrayList;
import java.util.Arrays;

import edu.sasq.Chickenkiller.android.*;
import android.R.array;
import android.app.Application;
import android.database.Cursor;
import android.widget.ArrayAdapter;

public class ApplicationExample extends Application {
	//Step 4.1
	//Step 4.2 popravi AndroidManifest.xml
	public ArrayList<rezultat> lista;
	public static String ime_privzeto="Default";
	public static int tocke=0;
	StevecArrayAdapter rezultati; //Step 4.9 Globalna lista

	DBAdapterStevec db;


	public void onCreate() {
        super.onCreate(); 
        db = new DBAdapterStevec(this); 
        lista = new ArrayList<rezultat>(); 
        fillFromDB();
        rezultati = new StevecArrayAdapter(this, R.layout.stevec_layout,lista); //Step 4.10 Globalna lista
   
	}	
	public void dobi(rezultat a){
		ime_privzeto=a.getIme();
		tocke=a.getPoints();
	}
	public  void add(rezultat a) {
		lista.add(a);
		addDB(a);	
		sort();
		}
	public void fillFromDB() {
		db.open();
		Cursor c = db.getAll();
		rezultat tmp;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			tmp = new rezultat();
			tmp.setIme(c.getString(DBAdapterStevec.POS_NAME));
			tmp.setPoints(c.getInt(DBAdapterStevec.POS_VALUE));
			tmp.setDbID(c.getLong(DBAdapterStevec.POS__ID));
			lista.add(tmp); 
		}
		sort();
		c.close();
		db.close();
	}
	public void addDB(rezultat s) {
		db.open();
		s.setDbID(db.insertStevc(s));
		db.close();	
	}
	public void remove(rezultat a) {
		if (a!=null)
		rezultati.remove(a);  
	}
	public void sort()
	{
		int n = lista.size();
	    for (int pass=1; pass < n; pass++) {  // count how many times
	        // This next loop becomes shorter and shorter
	        for (int i=0; i < n-pass; i++) {
	            if (lista.get(i).getPoints() < lista.get(i+1).getPoints()) {
	                // exchange elements
	               // rezultat temp = lista.get(i);  x[i] = x[i+1];  x[i+1] = temp;
	                rezultat temp = lista.get(i);   lista.set(i, lista.get(i+1)); lista.set(i+1, temp);
	            }
	        }
	    }
	}
}
