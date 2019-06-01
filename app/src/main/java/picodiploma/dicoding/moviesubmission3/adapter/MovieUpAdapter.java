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

public class MovieUpAdapter extends RecyclerView.Adapter<MovieUpAdapter.MovieUpViewHolder> {
    private ArrayList<MovieUpData> muData = new ArrayList<>();

    public void setMuData(ArrayList<MovieUpData>muItems) {
        muData.clear();
        muData.addAll(muItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieUpAdapter.MovieUpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_up, parent, false);
        return new MovieUpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieUpAdapter.MovieUpViewHolder holder, int position) {
        holder.bind(muData.get(position));
    }

    @Override
    public int getItemCount() {
        return muData.size();
    }

    class MovieUpViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.img_photo)
        ImageView imgPhoto;
        MovieUpViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(MovieUpData movieUpData) {
            txtTitle.setText(movieUpData.getTitle());
            txtDate.setText(movieUpData.getReleaseDate());
            Glide.with(itemView).load(movieUpData.getPosterPath())
                    .apply(new RequestOptions().override(120, 180)).into(imgPhoto);
        }
    }
}
