package model;

public class MusicFile {
	private String filename;
	private int track;
	private int madi;
	private int bpm;
	private String note;
	private String drumnote;
	
	public MusicFile(String filename, int track, int madi, int bpm, String note, String drumnote) {
		super();
		this.filename = filename;
		this.track = track;
		this.madi = madi;
		this.bpm = bpm;
		this.note = note;
		this.drumnote = drumnote;
	}
	
	public MusicFile() {
		// TODO Auto-generated constructor stub
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getTrack() {
		return track;
	}

	public void setTrack(int track) {
		this.track = track;
	}

	public int getMadi() {
		return madi;
	}

	public void setMadi(int madi) {
		this.madi = madi;
	}

	public int getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDrumnote() {
		return drumnote;
	}

	public void setDrumnote(String drumnote) {
		this.drumnote = drumnote;
	}
	
	
	
}
