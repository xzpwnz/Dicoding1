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
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MoviePopulerData;

public class MoviePopularAdapter extends RecyclerView.Adapter<MoviePopularAdapter.MoviePopularViewHolder> {
    private ArrayList<MoviePopulerData> mpData = new ArrayList<>();

    public void setMpData(ArrayList<MoviePopulerData> items) {
        this.mpData.clear();
        mpData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviePopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_popular, parent, false);
        return new MoviePopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePopularViewHolder holder, int position) {
        holder.bind(mpData.get(position));
    }

    @Override
    public int getItemCount() {
        return mpData.size();
    }

    class MoviePopularViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_photo)
        ImageView imgPhoto;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_popularity)
        TextView txtPopularity;

        MoviePopularViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(MoviePopulerData populerData) {
            txtTitle.setText(populerData.getTitle());
            txtPopularity.setText(String.valueOf(populerData.getPopularity()));
            Glide.with(itemView).load(populerData.getPosterPath())
                    .apply(new RequestOptions().override(120, 180))
                    .into(imgPhoto);
        }
    }
}
