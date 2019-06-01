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

public class TvShowPopularAdapter extends RecyclerView.Adapter<TvShowPopularAdapter.TvShowPopularViewHolder> {
    private List<TvShowPopularData> tvpData;
    private Context context;

    public TvShowPopularAdapter(Context context, List<TvShowPopularData> tvpItems) {
        this.context = context;
        this.tvpData = tvpItems;
    }


    @NonNull
    @Override
    public TvShowPopularAdapter.TvShowPopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_show_popular, parent, false);
        return new TvShowPopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowPopularAdapter.TvShowPopularViewHolder holder, int position) {
        String imageUrl = Config.IMAGE_URL_BASE_PATH + tvpData.get(position).getPosterPath();
        Glide.with(context).load(imageUrl)
                .apply(new RequestOptions().override(120, 180)).into(holder.imgPhoto);

        holder.txtTitle.setText(tvpData.get(position).getName());
        holder.txtPopular.setText(String.valueOf(tvpData.get(position).getPopularity()));
    }

    @Override
    public int getItemCount() {
        return tvpData.size();
    }

    class TvShowPopularViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_popularity)
        TextView txtPopular;
        @BindView(R.id.img_photo)
        ImageView imgPhoto;
        TvShowPopularViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
