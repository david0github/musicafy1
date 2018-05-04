package ser210.quinnipiac.edu.musicafy;

/**
 * Created by davidnguyen on 4/26/18.
 */

//getters and setters for artists data

public class ArtistData {

     int id;
     String artistFirstName;
     String homeTown;
     String albums;
     String recordLabel;
   //String image_url;
     String artistLastName;
     String genre;

    public ArtistData(int id, String artistFirstName,String artistLastName, String homeTown, String recordLabel, String albums, String genre){
        this.artistFirstName = artistFirstName;
        this.id = id;
        this.genre = genre;
        this.artistLastName = artistLastName;
        this.homeTown = homeTown;
        this.albums = albums;
        this.recordLabel = recordLabel;
       // this.image_url = image_url;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getArtistFirstName(){
        return artistFirstName;
    }

    public void setArtistFirstName(String artistFirstName){
        this.artistFirstName = artistFirstName;
    }

    public String getArtistLastName(){
        return artistLastName;
    }

    public void setArtistLastName(String artistLastName){
        this.artistLastName = artistLastName;
    }

    public String getAlbums(){
        return albums;
    }

    public void setAlbums(String albums){
        this.albums = albums;
    }

    public String getHomeTown(){
        return homeTown;
    }

    public void setHomeTown(String homeTown){
        this.homeTown = homeTown;
    }

    public String getRecordLabel(){
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel){
        this.recordLabel = recordLabel;
    }

}