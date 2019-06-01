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
import picodiploma.dicoding.moviesubmission3.activity.detailmovieactivity.DetailMovieUpActivity;
import picodiploma.dicoding.moviesubmission3.adapter.MovieUpActivityAdapter;
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MovieUpData;
import picodiploma.dicoding.moviesubmission3.utils.ItemClickSupport;
import picodiploma.dicoding.moviesubmission3.viewmodel.movieviewmodel.MovieUpViewModel;

public class MovieUpActivity extends AppCompatActivity {
    MovieUpActivityAdapter muaAdapter;
    @BindView(R.id.rv_movie_up)
    RecyclerView rvUpMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_up);
        ButterKnife.bind(this);
        MovieUpViewModel muViewModel;

        showRecycleList();

        muViewModel = ViewModelProviders.of(this).get(MovieUpViewModel.class);
        muViewModel.setMovieUp();
        muViewModel.getMovieUp().observe(this, getMovieUpData);
    }

    private Observer<ArrayList<MovieUpData>> getMovieUpData = new Observer<ArrayList<MovieUpData>>() {
        @Override
        public void onChanged(ArrayList<MovieUpData> movieUpData) {
            if (movieUpData != null) {
                muaAdapter.setMuaData(movieUpData);
                ItemClickSupport.addTo(rvUpMovie).setOnItemClickListener(
                        (recyclerView, position, v) -> showSelectedMovie(movieUpData.get(position)));
            }
        }
    };

    private void showSelectedMovie(MovieUpData upData) {
        Intent muaIntent = new Intent(MovieUpActivity.this, DetailMovieUpActivity.class);
        muaIntent.putExtra(DetailMovieUpActivity.EXTRA_MOVIE_UP, upData);
        startActivity(muaIntent);
    }

    private void showRecycleList() {
        muaAdapter = new MovieUpActivityAdapter();
        muaAdapter.notifyDataSetChanged();
        rvUpMovie.setLayoutManager(new LinearLayoutManager(this));
        rvUpMovie.setAdapter(muaAdapter);
    }


}
