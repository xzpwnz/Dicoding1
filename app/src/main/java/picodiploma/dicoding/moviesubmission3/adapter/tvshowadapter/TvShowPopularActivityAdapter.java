package picodiploma.dicoding.moviesubmission3.adapter.tvshowadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import picodiploma.dicoding.moviesubmission3.R;
import picodiploma.dicoding.moviesubmission3.models.tvshowmodels.TvShowPopularData;
import picodiploma.dicoding.moviesubmission3.utils.Config;

public class TvShowPopularActivityAdapter extends
        RecyclerView.Adapter<TvShowPopularActivityAdapter.TvShowPopularActivityViewHolder> {

    private Context context;
    private List<TvShowPopularData> tvpaData;

    public TvShowPopularActivityAdapter(
            Context context, List<TvShowPopularData> tvpaItems) {
        this.context = context;
        this.tvpaData = tvpaItems;
    }

    @NonNull
    @Override
    public TvShowPopularActivityAdapter.TvShowPopularActivityViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tv_show_popular_activity, parent, false);
        return new TvShowPopularActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull TvShowPopularActivityAdapter.TvShowPopularActivityViewHolder holder,
            int position) {
        holder.txtTitle.setText(tvpaData.get(position).getName());
        holder.txtDate.setText(tvpaData.get(position).getFirstAirDate());
        holder.txtLanguage.setText(tvpaData.get(position).getOriginalLanguage());
        holder.txtPopularity.setText(String.valueOf(tvpaData.get(position).getPopularity()));
        holder.txtVoteAverage.setText(String.valueOf(tvpaData.get(position).getVoteAverage()));

        String imageUrl = Config.IMAGE_URL_BASE_PATH + tvpaData.get(position).getPosterPath();
        Glide.with(context).load(imageUrl)
                .apply(new RequestOptions().override(100, 150)).into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return tvpaData.size();
    }

    class TvShowPopularActivityViewHolder extends RecyclerView.ViewHolder {
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
        TvShowPopularActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
