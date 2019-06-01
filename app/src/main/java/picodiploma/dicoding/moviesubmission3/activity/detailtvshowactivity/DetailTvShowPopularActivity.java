package picodiploma.dicoding.moviesubmission3.activity.detailtvshowactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import picodiploma.dicoding.moviesubmission3.R;
import picodiploma.dicoding.moviesubmission3.models.tvshowmodels.TvShowPopularData;
import picodiploma.dicoding.moviesubmission3.utils.Config;

public class DetailTvShowPopularActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW_POPULAR = "extra_tvshow_popular";
    TvShowPopularData tvShowPopularData;
    @BindView(R.id.txt_overview)
    TextView txtOverview;
    @BindView(R.id.txt_vote_average)
    TextView txtVoteAverage;
    @BindView(R.id.txt_date)
    TextView txtDate;
    @BindView(R.id.txt_language)
    TextView txtLanguage;
    @BindView(R.id.txt_popularity)
    TextView txtPopularity;
    @BindView(R.id.txt_vote_count)
    TextView txtVoteCount;
    @BindView(R.id.img_photo)
    ImageView imgPhoto;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show_popular);

        tvShowPopularData = getIntent().getParcelableExtra(EXTRA_TVSHOW_POPULAR);
        txtOverview.setText(tvShowPopularData.getOverview());
        txtDate.setText(tvShowPopularData.getFirstAirDate());
        txtLanguage.setText(tvShowPopularData.getOriginalLanguage());
        txtPopularity.setText(String.valueOf(tvShowPopularData.getPopularity()));
        txtVoteAverage.setText(String.valueOf(tvShowPopularData.getVoteAverage()));
        txtVoteCount.setText(String.valueOf(tvShowPopularData.getVoteCount()));

        String poster = tvShowPopularData.getPosterPath();
        String result = Config.IMAGE_URL_BASE_PATH + poster;
        Glide.with(this).load(result).apply(new RequestOptions()).into(imgPhoto);

        collapsingToolbarLayout.setTitle(tvShowPopularData.getName());
    }
}
