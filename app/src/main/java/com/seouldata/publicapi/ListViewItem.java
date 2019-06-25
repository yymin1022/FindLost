package com.seouldata.publicapi;

public class ListViewItem {
    private String nameStr ;
    private String placeStr ;
	private String fromStr;
	private String dateStr;
	private String idStr;

    public void setName(String name) {
        nameStr = name ;
    }
	
    public void setPlace(String place) {
        placeStr = place ;
    }
	
	public void setFrom(String from) {
        fromStr = from ;
    }

    public void setDate(String date) {
        dateStr = date ;
    }
	
	public void setId(String id){
		idStr = id;
	}

    public String getName() {
        return this.nameStr ;
    }
	
    public String getPlace() {
        return this.placeStr ;
    }
	
	public String getFrom() {
        return this.fromStr ;
    }

    public String getDate() {
        return this.dateStr ;
    }
	
	public String getId() {
		return this.idStr;
	}
}
