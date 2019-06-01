package picodiploma.dicoding.moviesubmission3.models.moviemodels;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

public class MovieUpData implements Parcelable {
    private String title;
    private int voteCount;
    private String posterPath;
    private String overview;
    private String releaseDate;
    private String languange;
    private Number voteAvetage;
    private Number popularity;
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

    public Number getVoteAvetage() {
        return voteAvetage;
    }

    public void setVoteAvetage(Number voteAvetage) {
        this.voteAvetage = voteAvetage;
    }

    public Number getPopularity() {
        return popularity;
    }

    public void setPopularity(Number popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MovieUpData(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.title = object.getString("title");
            this.overview = object.getString("overview");
            this.releaseDate = object.getString("release_date");
            this.languange = object.getString("original_language");
            String poster= object.getString("poster_path" );
            this.posterPath = "https://image.tmdb.org/t/p/w342/" + poster;
            this.voteCount = object.getInt("vote_count");
            this.voteAvetage = (Number) object.get("vote_average");
            this.popularity = (Number) object.get("popularity");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error Data", e.getMessage());
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.voteCount);
        dest.writeString(this.posterPath);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.languange);
        dest.writeSerializable(this.voteAvetage);
        dest.writeSerializable(this.popularity);
        dest.writeInt(this.id);
    }

    protected MovieUpData(Parcel in) {
        this.title = in.readString();
        this.voteCount = in.readInt();
        this.posterPath = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.languange = in.readString();
        this.voteAvetage = (Number) in.readSerializable();
        this.popularity = (Number) in.readSerializable();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<MovieUpData> CREATOR = new Parcelable.Creator<MovieUpData>() {
        @Override
        public MovieUpData createFromParcel(Parcel source) {
            return new MovieUpData(source);
        }

        @Override
        public MovieUpData[] newArray(int size) {
            return new MovieUpData[size];
        }
    };
}
