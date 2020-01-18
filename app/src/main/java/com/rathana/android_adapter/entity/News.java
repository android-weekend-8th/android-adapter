package com.rathana.android_adapter.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {

    private int thumbnail;
    private String title;
    private String date;
    private String author;
    private String imagePath;

    public News() {
    }

    protected News(Parcel in) {
        thumbnail = in.readInt();
        title = in.readString();
        date = in.readString();
        author = in.readString();
        imagePath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(thumbnail);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(author);
        dest.writeString(imagePath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "News{" +
                "thumbnail=" + thumbnail +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
