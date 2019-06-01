package picodiploma.dicoding.moviesubmission3.viewmodel.movieviewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import picodiploma.dicoding.moviesubmission3.models.moviemodels.MovieTopData;

public class MovieTopViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MovieTopData>> listMovieTop = new MutableLiveData<>();

    public void setMovieTop(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieTopData> listMtItem = new ArrayList<>();
        String API_KEY = "848001472ba79b519655a14b11681993";
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY + "&language=en-US&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray listMt = responseObject.getJSONArray("results");

                    for (int i = 0; i < listMt.length(); i++) {
                        JSONObject movieT = listMt.getJSONObject(i);
                        MovieTopData mtData = new MovieTopData(movieT);
                        listMtItem.add(mtData);
                    }
                    listMovieTop.postValue(listMtItem);
                } catch (Exception e) {
                    Log.d("Exxception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Failure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<MovieTopData>> getMovieTop() {
        return listMovieTop;
    }
}
