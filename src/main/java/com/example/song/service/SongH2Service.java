package com.example.song.service;
import com.example.song.model.Song;
import com.example.song.model.SongRowMapper;
import java.util.*; 


import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ExceptionDepthComparator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SongH2Service{

    @Autowired 
    private JdbcTemplate db;

    public ArrayList<Song> getSongs(){
        List<Song> songsList = db.query("select * from playlist",new SongRowMapper());
        ArrayList<Song> songs = new ArrayList<>(songsList);
        return songs;
    }

    public Song getSongById(int songId){
        try{
            Song song = db.queryForObject("select * from playlist where songId=?",new SongRowMapper(),songId);
            return song;

        } catch(Exception e){ 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        
    }


public Song addSong(Song song){
    db.update("insert into playlist(songName,lyricist,singer,musicDirector) values(?,?,?,?)", new SongRowMapper(),song.getSongName(),song.getLyricist(),song.getSinger(),song.getMusicDirector());

    Song addedSong = db.queryForObject("select * from playlist where songName=? and lyricist=?", new SongRowMapper(),song.getSongName(),song.getLyricist());
    return addedSong;
}

public Song updateSong(int songId,Song song){

    if(song.getSongName() != null){
        db.update("update playlist set songName=?  where songId=?",song.getSongName(),songId);
    }

    if(song.getLyricist() != null ){
        db.update("update playlist set lyricist=?  where songId=? ",song.getLyricist(),songId); 

    }

    if(song.getSinger() != null){
        db.update("update playlist set singer=? where songId=? ",song.getSinger(),songId);
    }

    if(song.getMusicDirector() != null){
        db.update("update playlist set musicDirector=? where songId=? ",song.getMusicDirector(),songId);
    } 

    return getSongById(songId);


}

public void deleteSong(int songId){
    db.update("delete from playlist where songId=?",songId);
}





}

