package ser210.quinnipiac.edu.musicafy;

/**
 * Created by davidnguyen on 4/26/18.
 */

public class ArtistData {

    String artistFirstName;
    String homeTown;
    String albums;
    String recordLabel;
    String image_url;
    String artistLastName;

    ArtistData(String artistFirstName,String artistLastName, String image_url, String homeTown, String recordLabel, String albums){
        this.artistFirstName = artistFirstName;
        this.artistLastName = artistLastName;
        this.homeTown = homeTown;
        this.albums = albums;
        this.recordLabel = recordLabel;
        this.image_url = image_url;


    }
}