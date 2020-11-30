package com.ftninformatika.zavrsniispitaad.Network;

import android.content.Context;

import androidx.room.Dao;

import com.ftninformatika.zavrsniispitaad.Model.Movie;

public class DatabaseHelpORM {
    public static final String DATABASE_NAME = "My Favourites";
    public static final int DATABASE_VERSION = 1;

    private Dao <Movie, String> movieDao = null;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

}

