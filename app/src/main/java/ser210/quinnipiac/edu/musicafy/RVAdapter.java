package ser210.quinnipiac.edu.musicafy;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by davidnguyen on 4/26/18.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ArtistViewHolder> {

    private static final String TAG = "RVAdapter";

    static List<ArtistData> mArtistList;
    static Context context;
    //Dialog myDialog;

    private int[] images ={R.drawable.jason, R.drawable.cardi, R.drawable.drake, R.drawable.breaking, R.drawable.imagine, R.drawable.ed,
            R.drawable.weeknd, R.drawable.post, R.drawable.kendrick, R.drawable.jcole};

    public RVAdapter(Context context, List<ArtistData> mArtistList){
        this.mArtistList = mArtistList;
        this.context = context;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.artist_item, viewGroup, false);
        final ArtistViewHolder artistViewHolder = new ArtistViewHolder(v);
        return artistViewHolder;

    }

    @Override
    public int getItemCount() {

        return mArtistList.size();
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder artistViewHolder, int position) {

        artistViewHolder.artistFirstName.setText(mArtistList.get(position).getArtistFirstName());
        artistViewHolder.artistLastName.setText(mArtistList.get(position).getArtistLastName());
        artistViewHolder.homeTown.setText(mArtistList.get(position).getHomeTown());
        artistViewHolder.recordLabel.setText(mArtistList.get(position).getRecordLabel());
        artistViewHolder.albums.setText(mArtistList.get(position).getAlbums());
        artistViewHolder.itemImage.setImageResource(images[position]);
    }

    //provide a reference to the views for each data item
    //complex data items may need more than one view per item, and
    //you provide access to all the views for a data item in a view holder
    public static class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //each data item is just a string in this case
        // public CardView cv;
        //public LinearLayout item;
        public int currentItem;
        public TextView artistFirstName;
        public TextView homeTown;
        public TextView albums;
        public TextView recordLabel;
        public ImageView itemImage;
        public TextView artistLastName;

        public ArtistViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
           // item = (LinearLayout) itemView.findViewById(R.id.item_artist_id);
            // artist_item = (LinearLayout) itemView.findViewById(R.id.item_artist_id);
            //cv = (CardView) itemView.findViewById(R.id.cv);
            artistFirstName = (TextView) itemView.findViewById(R.id.artist_firstName);
            artistLastName = (TextView) itemView.findViewById(R.id.artist_lastName);
            homeTown = (TextView) itemView.findViewById(R.id.home_town);
            albums = (TextView) itemView.findViewById(R.id.albums);
            recordLabel = (TextView) itemView.findViewById(R.id.recordLabel);
            itemImage = (ImageView) itemView.findViewById(R.id.artist_image);

        }

        public void add(int position, ArtistData artistData) {
            mArtistList.add(position, artistData);
        }

        public void remove(int position) {
            mArtistList.remove(position);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick " + getAdapterPosition() + " " + artistFirstName);
        }

    }
}