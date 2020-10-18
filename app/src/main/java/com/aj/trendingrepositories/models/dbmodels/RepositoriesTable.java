package com.aj.trendingrepositories.models.dbmodels;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Repositories")
public class RepositoriesTable implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int sID;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "avatar")
    public String avatar;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "language")
    public String language;

    @ColumnInfo(name = "languageColor")
    public String languageColor;

    @ColumnInfo(name = "stars")
    public Integer stars;

    @ColumnInfo(name = "forks")
    public Integer forks;

    @ColumnInfo(name = "currentPeriodStars")
    public Integer currentPeriodStars;

    public RepositoriesTable(String author, String name, String avatar, String url,
                             String description, String language, String languageColor, Integer stars,
                             Integer forks, Integer currentPeriodStars) {
        this.author = author;
        this.name = name;
        this.avatar = avatar;
        this.url = url;
        this.description = description;
        this.language = language;
        this.languageColor = languageColor;
        this.stars = stars;
        this.forks = forks;
        this.currentPeriodStars = currentPeriodStars;
    }

    protected RepositoriesTable(Parcel in) {
        sID = in.readInt();
        author = in.readString();
        name = in.readString();
        avatar = in.readString();
        url = in.readString();
        description = in.readString();
        language = in.readString();
        languageColor = in.readString();
        stars = in.readInt();
        forks = in.readInt();
        currentPeriodStars = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        sID = parcel.readInt();
        author = parcel.readString();
        name = parcel.readString();
        avatar = parcel.readString();
        url = parcel.readString();
        description = parcel.readString();
        language = parcel.readString();
        languageColor = parcel.readString();
        stars = parcel.readInt();
        forks = parcel.readInt();
        currentPeriodStars = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RepositoriesTable> CREATOR = new Creator<RepositoriesTable>() {
        @Override
        public RepositoriesTable createFromParcel(Parcel in) {
            return new RepositoriesTable(in);
        }

        @Override
        public RepositoriesTable[] newArray(int size) {
            return new RepositoriesTable[size];
        }
    };
}
