package picodiploma.dicoding.moviesubmission3.fragment.moviefragment;


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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import picodiploma.dicoding.moviesubmission3.R;
import picodiploma.dicoding.moviesubmission3.adapter.MoviePopularAdapter;
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MoviePopulerData;
import picodiploma.dicoding.moviesubmission3.ui.main.MovieViewModel.MoviePopularViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private MoviePopularAdapter mpAdapter;
    @BindView(R.id.rv_movie_populer)
    RecyclerView rvMoviePopular;

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
        ButterKnife.bind(this, view);

        mpAdapter = new MoviePopularAdapter();
        mpAdapter.notifyDataSetChanged();

        rvMoviePopular.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, view.isInLayout()));

        rvMoviePopular.setAdapter(mpAdapter);

        mpViewModel = ViewModelProviders.of(this).get(MoviePopularViewModel.class);
        mpViewModel.setMoviePopular();
        mpViewModel.getMoviePopular().observe(this,getMoviePopular);


    }

    private Observer<ArrayList<MoviePopulerData>> getMoviePopular = new Observer<ArrayList<MoviePopulerData>>() {
        @Override
        public void onChanged(ArrayList<MoviePopulerData> moviePopulerData) {
            if (moviePopulerData != null) {
                mpAdapter.setMpData(moviePopulerData);

            }
        }
    };
}
