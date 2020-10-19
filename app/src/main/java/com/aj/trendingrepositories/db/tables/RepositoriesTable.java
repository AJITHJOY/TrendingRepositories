package com.aj.trendingrepositories.db.tables;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Repositories", indices = {@Index(value = {"author", "name", "avatar",
        "url", "description", "language", "languageColor", "stars", "forks", "currentPeriodStars"}, unique = true)})
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

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageColor() {
        return languageColor;
    }

    public void setLanguageColor(String languageColor) {
        this.languageColor = languageColor;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getCurrentPeriodStars() {
        return currentPeriodStars;
    }

    public void setCurrentPeriodStars(Integer currentPeriodStars) {
        this.currentPeriodStars = currentPeriodStars;
    }

    @Override
    public String toString() {
        return "RepositoriesTable{" +
                "name='" + name + '\'' +
                '}';
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
