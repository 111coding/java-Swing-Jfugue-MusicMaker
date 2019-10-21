package model;

public class Track {
	  private int trackNum;
	  private String inst;
	  private String note;
	
	  public Track(int trackNum, String inst, String note) {
		super();
		this.trackNum = trackNum;
		this.inst = inst;
		this.note = note;
	}
	  
	  public Track() {
		// TODO Auto-generated constructor stub
	}

	public int getTrackNum() {
		return trackNum;
	}

	public void setTrackNum(int trackNum) {
		this.trackNum = trackNum;
	}

	public String getInst() {
		return inst;
	}

	public void setInst(String inst) {
		this.inst = inst;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	  
	  
}
