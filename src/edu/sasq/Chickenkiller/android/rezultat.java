package edu.sasq.Chickenkiller.android;

public class rezultat {
	
	private int points;
	private String ime;
	private long dbID;
	
	rezultat(int a, String x)
	{
		points=a;
		ime=x;
	}
	public rezultat() {
		// TODO Auto-generated constructor stub
	}
	public String getIme()
	{
		return ime;
	}
	public int getPoints()
	{
		return points;
	}
	public void setIme(String x)
	{
		this.ime=x;
	}
	public void setPoints(int a)
	{
		this.points=a;
	}
	public void setDbID(long dbID) {
		this.dbID = dbID;
	}
	public long getDbID() {
		return dbID;
	}

}
