package picodiploma.dicoding.moviesubmission3.activity.tvshowactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import picodiploma.dicoding.moviesubmission3.R;
import picodiploma.dicoding.moviesubmission3.activity.detailtvshowactivity.DetailTvShowPopularActivity;
import picodiploma.dicoding.moviesubmission3.adapter.tvshowadapter.TvShowPopularActivityAdapter;
import picodiploma.dicoding.moviesubmission3.models.tvshowmodels.TvShowPopularData;
import picodiploma.dicoding.moviesubmission3.utils.ItemClickSupport;
import picodiploma.dicoding.moviesubmission3.viewmodel.tvshowviewmodel.TvShowPopularViewModel;

public class TvShowPopularActivity extends AppCompatActivity {
    TvShowPopularViewModel tvpViewModel;
    TvShowPopularActivityAdapter tvpAdapter;


    @BindView(R.id.rv_tv_populer)
    RecyclerView rvTvPopuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_popular);
        ButterKnife.bind(this);
        rvTvPopuler.setLayoutManager(new LinearLayoutManager(this));

        tvpViewModel = ViewModelProviders.of(this).get(TvShowPopularViewModel.class);
        tvpViewModel.getTvShowPopular().observe(this, getTvShowPopularData);
    }

    private Observer<List<TvShowPopularData>> getTvShowPopularData = new Observer<List<TvShowPopularData>>() {
        @Override
        public void onChanged(List<TvShowPopularData> tvShowPopularData) {
            tvpAdapter = new TvShowPopularActivityAdapter(TvShowPopularActivity.this, tvShowPopularData);
            rvTvPopuler.setAdapter(tvpAdapter);
            ItemClickSupport.addTo(rvTvPopuler).setOnItemClickListener(
                    (recyclerView, position, v) -> {
                showSelectedTvShow(tvShowPopularData.get(position));
            });
        }
    };

    private void showSelectedTvShow(TvShowPopularData popularData) {
        Intent tvpIntent = new Intent(TvShowPopularActivity.this, DetailTvShowPopularActivity.class);
        tvpIntent.putExtra(DetailTvShowPopularActivity.EXTRA_TVSHOW_POPULAR, popularData);
        startActivity(tvpIntent);
    }

}
