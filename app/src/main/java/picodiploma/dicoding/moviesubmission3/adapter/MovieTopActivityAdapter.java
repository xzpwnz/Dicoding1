package picodiploma.dicoding.moviesubmission3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import picodiploma.dicoding.moviesubmission3.R;
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MovieTopData;

public class MovieTopActivityAdapter extends RecyclerView.Adapter<MovieTopActivityAdapter.MovieTopActivityViewHolder> {
    private ArrayList<MovieTopData> mtaData = new ArrayList<>();

    public void setMtaData(ArrayList<MovieTopData> itemMta) {
        mtaData.clear();
        mtaData.addAll(itemMta);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieTopActivityAdapter.MovieTopActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_top_activity, parent, false);
        return new MovieTopActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTopActivityAdapter.MovieTopActivityViewHolder holder, int position) {
        holder.bind(mtaData.get(position));
    }

    @Override
    public int getItemCount() {
        return mtaData.size();
    }

    class MovieTopActivityViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_popularity)
        TextView txtPopularity;
        @BindView(R.id.img_photo)
        ImageView imgPhoto;
        @BindView(R.id.txt_language)
        TextView txtLanguage;
        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.txt_vote_average)
        TextView txtVoteAverage;

        MovieTopActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(MovieTopData movieTopData) {
            txtTitle.setText(movieTopData.getTitle());
            txtDate.setText(movieTopData.getReleaseDate());
            txtLanguage.setText(movieTopData.getLanguange());
            txtPopularity.setText(String.valueOf(movieTopData.getPopularity()));
            txtVoteAverage.setText(String.valueOf(movieTopData.getVoteAvetage()));
            Glide.with(itemView).load(movieTopData.getPosterPath())
                    .apply(new RequestOptions().override(100, 150)).into(imgPhoto);
        }
    }
}
