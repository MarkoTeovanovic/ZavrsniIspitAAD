package com.ftninformatika.zavrsniispitaad.Network;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ftninformatika.zavrsniispitaad.Model.Movie;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;

public class DatabaseHelpORM extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "My Favourites";
    public static final int DATABASE_VERSION = 1;

    private Dao<Movie, String> movieDao = null;

    public DatabaseHelpORM(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, int configFileId, Dao<Movie, String> movieDao) {
        super(context, databaseName, factory, databaseVersion, configFileId);
        this.movieDao = movieDao;
    }

    public DatabaseHelpORM(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, File configFile, Dao<Movie, String> movieDao) {
        super(context, databaseName, factory, databaseVersion, configFile);
        this.movieDao = movieDao;
    }

    public DatabaseHelpORM(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, InputStream stream, Dao<Movie, String> movieDao) {
        super(context, databaseName, factory, databaseVersion, stream);
        this.movieDao = movieDao;
    }

    public DatabaseHelpORM(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    public DatabaseHelpORM(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, int configFileId) {
        super(context, databaseName, factory, databaseVersion, configFileId);
    }

    public DatabaseHelpORM(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, File configFile) {
        super(context, databaseName, factory, databaseVersion, configFile);
    }

    public DatabaseHelpORM(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, InputStream stream) {
        super(context, databaseName, factory, databaseVersion, stream);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Movie.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Movie.class, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

