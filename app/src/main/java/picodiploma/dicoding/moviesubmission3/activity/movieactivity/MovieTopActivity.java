package picodiploma.dicoding.moviesubmission3.activity.movieactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import picodiploma.dicoding.moviesubmission3.R;
import picodiploma.dicoding.moviesubmission3.activity.detailmovieactivity.DetailMovieTopActivity;
import picodiploma.dicoding.moviesubmission3.adapter.MovieTopActivityAdapter;
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MovieTopData;
import picodiploma.dicoding.moviesubmission3.viewmodel.movieviewmodel.MovieTopViewModel;
import picodiploma.dicoding.moviesubmission3.utils.ItemClickSupport;

public class MovieTopActivity extends AppCompatActivity {
    MovieTopActivityAdapter mtaAdapter;
    @BindView(R.id.rv_movie_top)
    RecyclerView rvMovieTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_top);
        ButterKnife.bind(this);
        MovieTopViewModel mtViewModel;

        showRecycleList();

        mtViewModel = ViewModelProviders.of(this).get(MovieTopViewModel.class);
        mtViewModel.setMovieTop();
        mtViewModel.getMovieTop().observe(this, getMovieTopData);
    }

    private Observer<ArrayList<MovieTopData>> getMovieTopData = new Observer<ArrayList<MovieTopData>>() {
        @Override
        public void onChanged(ArrayList<MovieTopData> movieTopData) {
            mtaAdapter.setMtaData(movieTopData);
            ItemClickSupport.addTo(rvMovieTop).setOnItemClickListener(
                    (recyclerView, position, v) -> showSelectedMovie(movieTopData.get(position)));
        }
    };

    private void showSelectedMovie(MovieTopData topData) {
        Intent mtaIntent = new Intent(MovieTopActivity.this, DetailMovieTopActivity.class);
        mtaIntent.putExtra(DetailMovieTopActivity.EXTRA_MOVIE_TOP, topData);
        startActivity(mtaIntent);
    }

    private void showRecycleList() {
        mtaAdapter = new MovieTopActivityAdapter();
        mtaAdapter.notifyDataSetChanged();
        rvMovieTop.setLayoutManager(new LinearLayoutManager(this));
        rvMovieTop.setAdapter(mtaAdapter);
    }
}
