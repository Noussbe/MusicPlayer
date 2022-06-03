package com.dam2022.musicplayer;

import android.net.Uri;

public class ModeleSong {


    private String songTitle;
    private String songArtist;
    private String songDuration;
    private Uri songURI;
    private Uri songCover;


    public ModeleSong(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public Uri getSongURI() {
        return songURI;
    }

    public void setSongURI(Uri songURI) {
        this.songURI = songURI;
    }

    public Uri getSongCover() {
        return songCover;
    }

    public void setSongCover(Uri songCover) {
        this.songCover = songCover;
    }
}
