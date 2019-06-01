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
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MovieUpData;

public class MovieUpActivityAdapter extends RecyclerView.Adapter<MovieUpActivityAdapter.MovieUpActivityViewHolder> {
    private ArrayList<MovieUpData> muaData = new ArrayList<>();

    public void setMuaData(ArrayList<MovieUpData> muaItems) {
        muaData.clear();
        muaData.addAll(muaItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieUpActivityAdapter.MovieUpActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_up_activity, parent, false);
        return new MovieUpActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieUpActivityAdapter.MovieUpActivityViewHolder holder, int position) {
        holder.bind(muaData.get(position));
    }

    @Override
    public int getItemCount() {
        return muaData.size();
    }

    class MovieUpActivityViewHolder extends RecyclerView.ViewHolder {
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

        MovieUpActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(MovieUpData movieUpData) {
            txtTitle.setText(movieUpData.getTitle());
            txtDate.setText(movieUpData.getReleaseDate());
            txtLanguage.setText(movieUpData.getLanguange());
            txtPopularity.setText(String.valueOf(movieUpData.getPopularity()));
            txtVoteAverage.setText(String.valueOf(movieUpData.getVoteAvetage()));
            Glide.with(itemView).load(movieUpData.getPosterPath())
                    .apply(new RequestOptions().override(100, 150)).into(imgPhoto);
        }
    }
}
