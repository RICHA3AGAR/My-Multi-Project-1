package com.tutorialspoint;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "movie")

public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;
	private int Id;
	private String Title;
	private Date ReleaseDate;
	private int RunningTimeMinutes;

	public Movie() {
	}

	public Movie(int id, String title, Date releaseDate, int runningTimeMinutes) {
		this.Id = id;
		this.Title = title;
		this.ReleaseDate = releaseDate;
		this.RunningTimeMinutes = runningTimeMinutes;
	}

	public int getId() {
		return Id;
	}

	@XmlElement
	public void setId(int id) {
		this.Id = id;
	}

	public String getTitle() {
		return Title;
	}

	@XmlElement
	public void setTitle(String title) {
		Title = title;
	}

	public Date getReleaseDate() {
		return ReleaseDate;
	}

	@XmlElement
	public void setReleaseDate(Date releaseDate) {
		ReleaseDate = releaseDate;
	}

	public int getRunningTimeMinutes() {
		return RunningTimeMinutes;
	}

	@XmlElement
	public void setRunningTimeMinutes(int runningTimeMinutes) {
		RunningTimeMinutes = runningTimeMinutes;
	}
}