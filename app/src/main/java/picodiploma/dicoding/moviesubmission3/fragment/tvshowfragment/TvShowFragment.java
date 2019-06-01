package picodiploma.dicoding.moviesubmission3.fragment.tvshowfragment;


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
import android.widget.TextView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import picodiploma.dicoding.moviesubmission3.R;
import picodiploma.dicoding.moviesubmission3.activity.detailtvshowactivity.DetailTvShowPopularActivity;
import picodiploma.dicoding.moviesubmission3.activity.tvshowactivity.TvShowPopularActivity;
import picodiploma.dicoding.moviesubmission3.adapter.tvshowadapter.TvShowPopularAdapter;
import picodiploma.dicoding.moviesubmission3.models.tvshowmodels.TvShowPopularData;
import picodiploma.dicoding.moviesubmission3.utils.ItemClickSupport;
import picodiploma.dicoding.moviesubmission3.viewmodel.tvshowviewmodel.TvShowPopularViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.rv_tv_populer)
    RecyclerView rvTvPopular;
    @BindView(R.id.txt_seeall)
    TextView txtSeeAll;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        rvTvPopular.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.HORIZONTAL, view.isInLayout()));

        TvShowPopularViewModel tvpViewModel;
        tvpViewModel = ViewModelProviders.of(this).get(TvShowPopularViewModel.class);
        tvpViewModel.getTvShowPopular().observe(this, getTvShowPopularData );

        txtSeeAll.setOnClickListener(this);
    }

    private Observer<List<TvShowPopularData>> getTvShowPopularData = new Observer<List<TvShowPopularData>>() {
        @Override
        public void onChanged(List<TvShowPopularData> tvShowPopularData) {
            TvShowPopularAdapter tvpAdapter;
            tvpAdapter = new TvShowPopularAdapter(getContext(), tvShowPopularData);
            rvTvPopular.setAdapter(tvpAdapter);
            ItemClickSupport.addTo(rvTvPopular).setOnItemClickListener((recyclerView, position, v) -> {

                Intent tvpIntent = new Intent(getActivity(), DetailTvShowPopularActivity.class);
                tvpIntent.putExtra(DetailTvShowPopularActivity.EXTRA_TVSHOW_POPULAR, tvShowPopularData.get(position));
                startActivity(tvpIntent);
            });
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.txt_seeall) {
            Intent popularIntent = new Intent(getActivity(), TvShowPopularActivity.class);
            startActivity(popularIntent);
        }
    }
}
