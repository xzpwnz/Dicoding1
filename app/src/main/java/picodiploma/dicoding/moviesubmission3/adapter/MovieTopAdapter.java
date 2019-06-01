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

public class MovieTopAdapter extends RecyclerView.Adapter<MovieTopAdapter.MovieTopViewHolder> {
    private ArrayList<MovieTopData> mtData = new ArrayList<>();

    public void setMtData(ArrayList<MovieTopData> mtItems){
        mtData.clear();
        mtData.addAll(mtItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieTopAdapter.MovieTopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_top, parent, false);
        return new MovieTopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTopAdapter.MovieTopViewHolder holder, int position) {
        holder.bind(mtData.get(position));
    }

    @Override
    public int getItemCount() {
        return mtData.size();
    }

    class MovieTopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_vote_average)
        TextView txtVoteAverage;
        @BindView(R.id.img_photo)
        ImageView imgPhoto;
        String vote = "/10";

        MovieTopViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind (MovieTopData movieTopData){
            String voteAverage = String.valueOf(movieTopData.getVoteAvetage());
            String resultVote = voteAverage + vote;
            txtTitle.setText(movieTopData.getTitle());
            txtVoteAverage.setText(resultVote);
            Glide.with(itemView).load(movieTopData.getPosterPath())
                    .apply(new RequestOptions().override(120, 180)).into(imgPhoto);
        }
    }
}
