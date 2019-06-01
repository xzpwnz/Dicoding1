package picodiploma.dicoding.moviesubmission3.fragment.moviefragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import picodiploma.dicoding.moviesubmission3.R;
import picodiploma.dicoding.moviesubmission3.activity.detailmovieactivity.DetailMoviePopularActivity;
import picodiploma.dicoding.moviesubmission3.activity.detailmovieactivity.DetailMovieTopActivity;
import picodiploma.dicoding.moviesubmission3.activity.detailmovieactivity.DetailMovieUpActivity;
import picodiploma.dicoding.moviesubmission3.activity.movieactivity.MoviePopularActivity;
import picodiploma.dicoding.moviesubmission3.activity.movieactivity.MovieTopActivity;
import picodiploma.dicoding.moviesubmission3.activity.movieactivity.MovieUpActivity;
import picodiploma.dicoding.moviesubmission3.adapter.MoviePopularAdapter;
import picodiploma.dicoding.moviesubmission3.adapter.MovieTopAdapter;
import picodiploma.dicoding.moviesubmission3.adapter.MovieUpAdapter;
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MoviePopulerData;
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MovieTopData;
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MovieUpData;
import picodiploma.dicoding.moviesubmission3.viewmodel.movieviewmodel.MoviePopularViewModel;
import picodiploma.dicoding.moviesubmission3.viewmodel.movieviewmodel.MovieTopViewModel;
import picodiploma.dicoding.moviesubmission3.utils.ItemClickSupport;
import picodiploma.dicoding.moviesubmission3.viewmodel.movieviewmodel.MovieUpViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements View.OnClickListener {
    private MoviePopularAdapter mpAdapter;
    private MovieTopAdapter mtAdapter;
    private MovieUpAdapter muAdapter;
    @BindView(R.id.rv_movie_populer)
    RecyclerView rvMoviePopular;
    @BindView(R.id.rv_movie_top)
    RecyclerView rvMovieTop;
    @BindView(R.id.rv_movie_up)
    RecyclerView rvMovieUp;
    @BindView(R.id.txt_seeall)
    TextView txtSeeAll;
    @BindView(R.id.txt_seeall_2)
    TextView txtSeeAll2;
    @BindView(R.id.txt_seeall_3)
    TextView txtSeeAll3;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MoviePopularViewModel mpViewModel;
        MovieTopViewModel mtViewModel;
        MovieUpViewModel muViewModel;

        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.VISIBLE);

        showRecycleMoviePopular(view);
        showRecycleMovieTop(view);
        showRecycleMovieUp(view);

        mpViewModel = ViewModelProviders.of(this).get(MoviePopularViewModel.class);
        mpViewModel.setMoviePopular();
        mpViewModel.getMoviePopular().observe(this, getMoviePopular);

        mtViewModel = ViewModelProviders.of(this).get(MovieTopViewModel.class);
        mtViewModel.setMovieTop();
        mtViewModel.getMovieTop().observe(this, getMovieTop);

        muViewModel = ViewModelProviders.of(this).get(MovieUpViewModel.class);
        muViewModel.setMovieUp();
        muViewModel.getMovieUp().observe(this, getMovieUp);

        txtSeeAll.setOnClickListener(this);
        txtSeeAll2.setOnClickListener(this);
        txtSeeAll3.setOnClickListener(this);
    }

    private Observer<ArrayList<MoviePopulerData>> getMoviePopular = new Observer<ArrayList<MoviePopulerData>>() {
        @Override
        public void onChanged(ArrayList<MoviePopulerData> moviePopulerData) {
            if (moviePopulerData != null) {
                mpAdapter.setMpData(moviePopulerData);
                progressBar.setVisibility(View.GONE);
                ItemClickSupport.addTo(rvMoviePopular).setOnItemClickListener(
                        (recyclerView, position, v) -> showSelectedPopularData(moviePopulerData.get(position)));
            }
        }
    };

    private Observer<ArrayList<MovieTopData>> getMovieTop = new Observer<ArrayList<MovieTopData>>() {
        @Override
        public void onChanged(ArrayList<MovieTopData> movieTopData) {
            if (movieTopData != null) {
                mtAdapter.setMtData(movieTopData);
                ItemClickSupport.addTo(rvMovieTop).setOnItemClickListener(
                        (recyclerView, position, v) -> showSelectedTopData(movieTopData.get(position)));
            }
        }
    };

    private Observer<ArrayList<MovieUpData>> getMovieUp = new Observer<ArrayList<MovieUpData>>() {
        @Override
        public void onChanged(ArrayList<MovieUpData> movieUpData) {
            if (movieUpData != null) {
                muAdapter.setMuData(movieUpData);
                ItemClickSupport.addTo(rvMovieUp).setOnItemClickListener(
                        (recyclerView, position, v) -> showSelectedUpData(movieUpData.get(position)));
            }
        }
    };

    private void showSelectedPopularData(MoviePopulerData populerData) {
        Intent mpIntent = new Intent(getActivity(), DetailMoviePopularActivity.class);
        mpIntent.putExtra(DetailMoviePopularActivity.EXTRA_MOVIE, populerData);
        startActivity(mpIntent);
    }

    private void showSelectedTopData(MovieTopData topData) {
        Intent mtIntent = new Intent(getActivity(), DetailMovieTopActivity.class);
        mtIntent.putExtra(DetailMovieTopActivity.EXTRA_MOVIE_TOP, topData);
        startActivity(mtIntent);
    }

    private void showSelectedUpData(MovieUpData upData) {
        Intent muIntent = new Intent(getActivity(), DetailMovieUpActivity.class);
        muIntent.putExtra(DetailMovieUpActivity.EXTRA_MOVIE_UP, upData);
        startActivity(muIntent);
    }

    private void showRecycleMovieUp(View view) {
        muAdapter = new MovieUpAdapter();
        muAdapter.notifyDataSetChanged();
        rvMovieUp.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, view.isInLayout()));
        rvMovieUp.setAdapter(muAdapter);
    }

    private void showRecycleMoviePopular(View view) {
        mpAdapter = new MoviePopularAdapter();
        mpAdapter.notifyDataSetChanged();
        rvMoviePopular.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, view.isInLayout()));
        rvMoviePopular.setAdapter(mpAdapter);
    }

    private void showRecycleMovieTop(View view) {
        mtAdapter = new MovieTopAdapter();
        mtAdapter.notifyDataSetChanged();
        rvMovieTop.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, view.isInLayout()));
        rvMovieTop.setAdapter(mtAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_seeall:
                Intent popularIntent = new Intent(getActivity(), MoviePopularActivity.class);
                startActivity(popularIntent);
                break;
            case R.id.txt_seeall_2:
                Intent topIntent = new Intent(getActivity(), MovieTopActivity.class);
                startActivity(topIntent);
                break;
            case R.id.txt_seeall_3:
                Intent upIntent = new Intent(getActivity(), MovieUpActivity.class);
                startActivity(upIntent);
        }
    }
}
