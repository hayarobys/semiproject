package db;

public class DodgeRankDTO {
	private String dname;
	private String ddate = "";
	private int drecord;
	
	public String getDname() {
		return dname;
	}
	
	public void setDname(String name) {
		this.dname = name;
	}
	
	public String getDdate() {
		return ddate;
	}
	
	public void setDdate(String date) {
		this.ddate = date;
	}
	
	public int getDrecord() {
		return drecord;
	}
	
	public void setDrecord(int record) {
		this.drecord = record;
	}
	
	
}	// class DodgeRank_DTO{}
