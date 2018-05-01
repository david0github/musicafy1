package ser210.quinnipiac.edu.musicafy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by davidnguyen on 4/18/18.
 */

public class dbHelper extends SQLiteOpenHelper {

    private static final String TABLE_CREATE = "CREATE TABLE songs(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, songName " +
            "VARCHAR(50) NOT NULL, " + " artistFirstName VARCHAR(20) NOT NULL, artistLastName VARCHAR(20), yearRelease " +
            "DATE NOT NULL, album VARCHAR(15), " + " producerLastName VARCHAR(20) NOT NULL, producerFirstName VARCHAR(20), " +
            "photo BLOB, lyrics TEXT)";

    private static final String TABLE_CREATE_ARTIST = "CREATE TABLE artists(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            " homeTown VARCHAR(20) NOT NULL, recordLabel VARCHAR(20) NOT NULL, photo BLOB, albums TEXT, artistFirstName VARCHAR(20)," +
            " artistLastName VARCHAR(20), FOREIGN KEY(artistFirstName) REFERENCES songs(artistFirstName))";

    //info for database on songs
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "songs.db";
    private static final String TABLE_NAME = "songs";
    private static final String TABLE_NAME_ARTISTS = "artists";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SONG = "songName";
    private static final String COLUMN_ARTISTFIRSTNAME = "artistFirstName";
    private static final String COLUMN_ARTISTLASTNAME = "artistLastName";
    private static final String COLUMN_YEAR = "yearRelease";
    private static final String COLUMN_ALBUM = "album";
    private static final String COLUMN_PRODUCERLAST = "producerLastName";
    private static final String COLUMN_PRODUCERFIRST = "producerFirstName";
    private static final String COLUMN_PHOTO = "photo";
    private static final String COLUMN_LYRICS = "lyrics";
    private static final String COLUMN_HOMETOWN = "homeTown";
    private static final String COLUMN_RECORDLABEL = "recordLabel";
    private static final String COLUMN_ALBUMS = "albums";

    SQLiteDatabase db;

    public dbHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void addArtist(String artistFirstName, String artistLastName, String homeTown, String recordLabel, String albums, byte[] logoImage){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ARTISTFIRSTNAME, artistFirstName);
        values.put(COLUMN_ARTISTLASTNAME, artistLastName);
        values.put(COLUMN_HOMETOWN, homeTown);
        values.put(COLUMN_RECORDLABEL, recordLabel);
        values.put(COLUMN_ALBUMS, albums);
        values.put(COLUMN_PHOTO, logoImage);
        db.insert(TABLE_NAME_ARTISTS, null, values);
        db.close();

    }

    /** public ArrayList<String> viewArtistValues(){
     db = this.getWritableDatabase();
     String query = String.format("SELECT artistFirstName, artistLastName, homeTown, recordLabel, albums FROM ", TABLE_NAME_ARTISTS);
     Cursor cursor = db.rawQuery(query, null);
     ArrayList<String> artist = new ArrayList<String>(){
     }
     } */

    public void addSong(String songName, String artistFirstName, String artistLastName, String yearRelease, String album,
                        String producerLastName, String producerFirstName, String photo, String lyrics){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG, songName);
        values.put(COLUMN_ARTISTFIRSTNAME, artistFirstName);
        values.put(COLUMN_ARTISTLASTNAME, artistLastName);
        values.put(COLUMN_YEAR, yearRelease);
        values.put(COLUMN_ALBUM, album);
        values.put(COLUMN_PRODUCERLAST, producerLastName);
        values.put(COLUMN_PRODUCERFIRST, producerFirstName);
        values.put(COLUMN_PHOTO, photo);
        values.put(COLUMN_LYRICS, lyrics);
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE_ARTIST);

    }

    @Override
    public void onUpgrade(SQLiteDatabase dp, int oldVersion, int newVersion){

        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        String query1 = "DROP TABLE IF EXISTS "+ TABLE_NAME_ARTISTS;
        db.execSQL(query);
        db.execSQL(query1);
        onCreate(db);


    }
}

