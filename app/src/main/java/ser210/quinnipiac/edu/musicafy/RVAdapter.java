package ser210.quinnipiac.edu.musicafy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by davidnguyen on 4/26/18.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ArtistViewHolder> {

    private List<ArtistData> artistData;

    RVAdapter(List<ArtistData> artistData) {

        this.artistData = artistData;

    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView artistFirstName;
        TextView homeTown;
        TextView ablums;
        TextView recordLabel;
        ImageView artistImage;
        TextView artistLastName;

        ArtistViewHolder(View itemView){

            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            artistFirstName = (TextView) itemView.findViewById(R.id.artist_firstName);
            artistLastName = (TextView) itemView.findViewById(R.id.artist_lastName);
            homeTown = (TextView) itemView.findViewById(R.id.home_town);
            ablums = (TextView) itemView.findViewById(R.id.albums);
            recordLabel = (TextView) itemView.findViewById(R.id.recordLabel);
            artistImage = (ImageView) itemView.findViewById(R.id.artist_image);

        }
    }

    @Override
    public int getItemCount(){
        return artistData.size();
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.artist_item, viewGroup, false);
        ArtistViewHolder avh = new ArtistViewHolder(v);
        return avh;

    }

    @Override
    public void onBindViewHolder(ArtistViewHolder artistViewHolder, int i){
        artistViewHolder.artistFirstName.setText(artistData.get(i).artistFirstName);
        artistViewHolder.homeTown.setText(artistData.get(i).homeTown);
        artistViewHolder.artistLastName.setText(artistData.get(i).artistLastName);
        artistViewHolder.recordLabel.setText(artistData.get(i).recordLabel);
        artistViewHolder.ablums.setText(artistData.get(i).albums);
        new DownloadImageTask(artistViewHolder.artistImage).execute(artistData.get(i).image_url);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage){
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls){
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try{
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExcute(Bitmap result){
            bmImage.setImageBitmap(result);
        }
    }
}