package com.sddevops.jenkins_maven.eclipse;

import java.util.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SongCollection {
	
	private ArrayList<Song> songs = new ArrayList<>();
	private int capacity;
	private LocalDateTime timeCreated;

    public SongCollection() {
    	/*songs.add(new Song("0001","good 4 u","Olivia Rodrigo",3.59));
    	songs.add(new Song("0002","Peaches","Justin Bieber",3.18));
    	songs.add(new Song("0003","MONTERO","Lil Nas X",2.3));
    	songs.add(new Song("0004","bad guy","Billie Eilish",3.14));*/

        this.capacity = 20;
        this.timeCreated = LocalDateTime.now();
    }

    public SongCollection(int capacity) {
        this.capacity = capacity;
        this.timeCreated = LocalDateTime.now();
    }
    
    public String getFullDateCreated() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	return this.timeCreated.format(formatter);
    }
    
    public String getYearCreated() {
    	return String.valueOf(this.timeCreated.getYear()); 
    }
    
    public String compareCollection(SongCollection other) {
    	if (this.timeCreated.isBefore(other.timeCreated)) {
    		return "My collection is older!";
    	} else if (this.timeCreated.isEqual(other.timeCreated)) {
    		return "My collection was created at the same time!";
    	} else {
    		return "My collection is newer!";
    	}
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
    	if(songs.size() != capacity) {
    		songs.add(song);
    	}
    }
    
    public ArrayList<Song> sortSongsByTitle() {         
        Collections.sort(songs, Song.titleComparator);         
        return songs;     
    } 
    
    public ArrayList<Song> sortSongsBySongLength() {         
        Collections.sort(songs, Song.songLengthComparator);         
        return songs;     
    } 
    
    public Song findSongsById(String id) {
    	for (Song s : songs) { 		      
            if(s.getId().equals(id)) return s;
       }
    	return null;
    }

    public Song findSongByTitle(String title) {
    	for (Song s : songs) { 		      
            if(s.getTitle().equals(title)) return s;
       }
    	return null;
    }
}
