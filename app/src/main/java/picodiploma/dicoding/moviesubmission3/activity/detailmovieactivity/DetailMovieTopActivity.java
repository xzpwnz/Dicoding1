package picodiploma.dicoding.moviesubmission3.activity.detailmovieactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import picodiploma.dicoding.moviesubmission3.R;
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MovieTopData;

public class DetailMovieTopActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE_TOP = "extra_movie_top";
    MovieTopData movieTopData;
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
        setContentView(R.layout.activity_detail_movie_top);
        ButterKnife.bind(this);

        movieTopData = getIntent().getParcelableExtra(EXTRA_MOVIE_TOP);

        txtOverview.setText(movieTopData.getOverview());
        txtDate.setText(movieTopData.getReleaseDate());
        txtLanguage.setText(movieTopData.getLanguange());
        txtVoteCount.setText(String.valueOf(movieTopData.getVoteCount()));
        txtPopularity.setText(String.valueOf(movieTopData.getPopularity()));
        String voteAverage = movieTopData.getVoteAvetage().toString();
        String vote = "/10";
        String result = voteAverage + vote;
        txtVoteAverage.setText(result);
        collapsingToolbarLayout.setTitle(movieTopData.getTitle());

        Glide.with(this)
                .load(movieTopData.getPosterPath())
                .apply(new RequestOptions()).into(imgPhoto);


    }
}
