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

        db = this.getWritableDatabase();
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
    public ArtistData getArtist(int key){
        ArtistData artistData = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {dbHelper.COL_ID, dbHelper.COL_ARTISTFIRSTNAME, dbHelper.COL_ARTISTLASTNAME, dbHelper.COL_HOMETOWN, dbHelper.COL_RECORDLABEL, dbHelper.COL_ALBUMS};
        Cursor cursor = db.query(dbHelper.TABLE_ARTISTS, columns, dbHelper.COL_ARTISTFIRSTNAME+" = '"+key+"'", null, null, null, null);
        if(cursor.moveToFirst()){
            int index = cursor.getColumnIndex(dbHelper.COL_ID);
            int index1 = cursor.getColumnIndex(dbHelper.COL_ARTISTFIRSTNAME);
            int index2 = cursor.getColumnIndex(dbHelper.COL_ARTISTLASTNAME);
            int index3 = cursor.getColumnIndex(dbHelper.COL_HOMETOWN);
            int index4 = cursor.getColumnIndex(dbHelper.COL_RECORDLABEL);
            int index5 = cursor.getColumnIndex(dbHelper.COL_ALBUMS);
            int id = cursor.getInt(index);
            String artistFirstName = cursor.getString(index1);
            String artistLastName = cursor.getString(index2);
            String homeTown = cursor.getString(index3);
            String recordLabel = cursor.getString(index4);
            String albums = cursor.getString(index5);
            artistData = new ArtistData(id, artistFirstName, artistLastName, homeTown, recordLabel, albums);

        }
        return artistData;

    }

    //getting all artists
    public List<ArtistData> getAllArtists(){

        List<ArtistData> artists = new ArrayList<>();

        String allArtist = "Select * from " + TABLE_ARTISTS;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(allArtist, null);
        if (cursor.moveToFirst()) {
            do {
                ArtistData artistData = new ArtistData();
                artistData.setArtistFirstName(cursor.getString(1));
                artistData.setArtistLastName(cursor.getString(2));
                artistData.setHomeTown(cursor.getString(3));
                artistData.setRecordLabel(cursor.getString(4));
                artistData.setAlbums(cursor.getString(5));

                artists.add(artistData);

            }while(cursor.moveToNext());
        }
        db.close();
        return artists;
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

}

