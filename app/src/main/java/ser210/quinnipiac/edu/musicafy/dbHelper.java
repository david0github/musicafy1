package ser210.quinnipiac.edu.musicafy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by davidnguyen on 4/18/18.
 */

public class dbHelper extends SQLiteOpenHelper {

    //database version
    private static final int DATABASE_VERSION = 1;
    //database name
    private static final String DATABASE_NAME = "ARTIST";
    //contacts table name
    private static final String TABLE_ARTISTS = "ARTIST_TABLE";
    //artist table columns names
    private static final String COL_ID = "id";
    private static final String COL_ARTISTFIRSTNAME = "artistFirstName";
    private static final String COL_ARTISTLASTNAME = "artistLastName";
    private static final String COL_HOMETOWN = "homeTown";
    private static final String COL_RECORDLABEL = "recordLabel";
    //private static final String COLUMN_PHOTO = "photo";
    private static final String COL_ALBUMS = "albums";

    SQLiteDatabase db;

    public dbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String TABLE_CREATE_ARTIST = "CREATE TABLE " + TABLE_ARTISTS + "(" +  COL_ID + " INTEGER PRIMARY KEY, " + COL_ARTISTFIRSTNAME + " TEXT, "
                + COL_ARTISTLASTNAME + " TEXT,  " + COL_HOMETOWN + " TEXT, " + COL_RECORDLABEL + " TEXT, " + COL_ALBUMS + " TEXT);";

        db.execSQL(TABLE_CREATE_ARTIST);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTISTS);
        //creating tables again
        onCreate(db);
    }

    public void insertArtist(String artistFirst, String artistLast, String hometown, String  recordLabel, String  albums) {

        db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_ARTISTFIRSTNAME, artistFirst);
        values.put(COL_ARTISTLASTNAME, artistLast);
        values.put(COL_HOMETOWN, hometown);
        values.put(COL_RECORDLABEL, recordLabel);
        values.put(COL_ALBUMS, albums);

        db.insert(TABLE_ARTISTS, null, values);
        db.close();

    }

    // adding a new artist
    public void addArtist(ArtistData artistData){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_ARTISTFIRSTNAME, artistData.getArtistFirstName());
        values.put(COL_ARTISTLASTNAME, artistData.getArtistLastName());
        values.put(COL_HOMETOWN, artistData.getHomeTown());
        values.put(COL_RECORDLABEL, artistData.getRecordLabel());
        values.put(COL_ALBUMS, artistData.getAlbums());

        //inserting rows
        db.insert(TABLE_ARTISTS, null, values);

        //closing database connection
        db.close();

    }

    //getting one artist
    public ArtistData getArtist(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ARTISTS, new String[]{ COL_ID,
                COL_ARTISTFIRSTNAME, COL_ARTISTLASTNAME, COL_HOMETOWN,
                COL_RECORDLABEL, COL_ALBUMS}, COL_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ArtistData contact =  new ArtistData(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        //return artist
        return contact;
    }

    //getting all artists
    public ArrayList<ArtistData> getAllArtists(){

        ArrayList<ArtistData> artists = new ArrayList<>();

        db = this.getReadableDatabase();

        String allArtist = "Select * from " + TABLE_ARTISTS;

        Cursor cursor = db.rawQuery(allArtist, null);

        if (cursor.moveToFirst()) {
            do {

                ArtistData artistData = new ArtistData(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                artists.add(artistData);

                Log.d("ID: ", String.valueOf(cursor.getInt(0)));

            } while (cursor.moveToNext());
        }

        db.close();
        return artists;
    }

    public List<ArtistData> artistList(String filter){
        String query;
        query = "SELECT * FROM " + TABLE_ARTISTS;

        List<ArtistData> artistLinkedList = new LinkedList<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArtistData artistData;

        if(cursor.moveToFirst()) {
            do {
                artistData = new ArtistData();

                artistData.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                artistData.setArtistFirstName(cursor.getString(cursor.getColumnIndex(COL_ARTISTFIRSTNAME)));
                artistData.setArtistLastName(cursor.getString(cursor.getColumnIndex(COL_ARTISTLASTNAME)));
                artistData.setHomeTown(cursor.getString(cursor.getColumnIndex(COL_HOMETOWN)));
                artistData.setRecordLabel(cursor.getString(cursor.getColumnIndex(COL_RECORDLABEL)));
                artistData.setAlbums(cursor.getString(cursor.getColumnIndex(COL_ALBUMS)));
                artistLinkedList.add(artistData);
            } while (cursor.moveToNext());
        }

        return artistLinkedList;
    }

    //getting artists count
    public int getArtistsCount(){
        String countQuery = "SELECT * FROM " + TABLE_ARTISTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    //updating a artist
    public int updateArtist(ArtistData artistData){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_ARTISTFIRSTNAME, artistData.getArtistFirstName());
        values.put(COL_ARTISTLASTNAME, artistData.getArtistLastName());
        values.put(COL_HOMETOWN, artistData.getHomeTown());
        values.put(COL_RECORDLABEL, artistData.getRecordLabel());
        values.put(COL_ALBUMS, artistData.getAlbums());

        //updating row
        return db.update(TABLE_ARTISTS, values, COL_ID + "=?",
                new String[]{String.valueOf(artistData.getId())});
    }

    //deleting a artist
    public void deleteArtist(ArtistData artistData){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ARTISTS, COL_ID + "=?", new String[]{String.valueOf(artistData.getId())});
        db.close();
    }



/**
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

    public void addArtist(String artistFirstName, String artistLastName, String homeTown, String recordLabel, String albums){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ARTISTFIRSTNAME, artistFirstName);
        values.put(COLUMN_ARTISTLASTNAME, artistLastName);
        values.put(COLUMN_HOMETOWN, homeTown);
        values.put(COLUMN_RECORDLABEL, recordLabel);
        values.put(COLUMN_ALBUMS, albums);
        long newRowId;
        newRowId = db.insert(TABLE_NAME_ARTISTS, null, values);
        db.close();

    }

    /** public ArrayList<String> viewArtistValues(){
     db = this.getWritableDatabase();
     String query = String.format("SELECT artistFirstName, artistLastName, homeTown, recordLabel, albums FROM ", TABLE_NAME_ARTISTS);
     Cursor cursor = db.rawQuery(query, null);
     ArrayList<String> artist = new ArrayList<String>(){
     }
     } */
/**
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
    public void onUpgrade(SQLiteDatabase dp, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        String query1 = "DROP TABLE IF EXISTS " + TABLE_NAME_ARTISTS;
        db.execSQL(query);
        db.execSQL(query1);
        onCreate(db);


    } */
}

