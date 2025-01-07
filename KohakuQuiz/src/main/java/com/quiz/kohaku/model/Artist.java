package com.quiz.kohaku.model;

public class Artist {
	private int participation_id;
	private String artist_name;
	private String artist_song;
	private int year;
	private int appearance;
	private String team;
	
	public int getParticipation_id() {
		return participation_id;
	}
	public void setParticipation_id(int participation_id) {
		this.participation_id = participation_id;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getArtist_song() {
		return artist_song;
	}
	public void setArtist_song(String artist_song) {
		this.artist_song = artist_song;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getAppearance() {
		return appearance;
	}
	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	
	
}
