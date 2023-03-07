package com.example.song.model; 
import com.example.song.model.Song; 

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.SQLExceptionSubclassTranslator;

import java.sql.SQLException; 
import java.sql.ResultSet; 

public class SongRowMapper implements RowMapper<Song>  {

    public Song mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new Song(
            rs.getInt("songId"),
            rs.getString("songName"),
            rs.getString("lyricist"),
            rs.getString("singer"),
            rs.getString("musicDirector")
        );
    }
}