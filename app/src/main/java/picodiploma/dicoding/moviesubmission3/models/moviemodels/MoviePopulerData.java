package picodiploma.dicoding.moviesubmission3.models.moviemodels;

import android.util.Log;

import org.json.JSONObject;

public class MoviePopulerData {
    private String title;
    private int voteCount;
    private String posterPath;
    private String overview;
    private String releaseDate;
    private String languange;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLanguange() {
        return languange;
    }

    public void setLanguange(String languange) {
        this.languange = languange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MoviePopulerData(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.title = object.getString("title");
            this.overview = object.getString("overview");
            this.releaseDate = object.getString("release_date");
            this.languange = object.getString("original_language");
            String poster= object.getString("poster_path" );
            this.posterPath = "https://image.tmdb.org/t/p/w92/" + poster;
            this.voteCount = object.getInt("vote_count");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error Data", e.getMessage());
        }
    }
}
