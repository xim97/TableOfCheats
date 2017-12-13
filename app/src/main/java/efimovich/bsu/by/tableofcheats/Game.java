package efimovich.bsu.by.tableofcheats;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {
    private String name;
    private int yearOfRelease;
    private boolean isFavorite;
    private String cheatsRU;
    private String cheatsEN;

    public Game() {
        name = "asdf";
        yearOfRelease = 1000;
        isFavorite = false;
    }

    public Game(String name, int yearOfRelease, boolean isFavorite) {
        this.name = name;
        this.yearOfRelease = yearOfRelease;
        this.isFavorite = isFavorite;
    }

    public Game(String name, int yearOfRelease, boolean isFavorite, String cheatsRU, String cheatsEN) {
        this.name = name;
        this.yearOfRelease = yearOfRelease;
        this.isFavorite = isFavorite;
        this.cheatsRU = cheatsRU;
        this.cheatsEN = cheatsEN;
    }

    private Game(Parcel in) {
        this.name = in.readString();
        this.yearOfRelease = in.readInt();
        this.isFavorite = (in.readInt() != 0);
        this.cheatsRU = in.readString();
        this.cheatsEN = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public String getYearOfReleaseByString() {
        return Integer.valueOf(yearOfRelease).toString();
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheatsRU() {
        return cheatsRU;
    }

    public void setCheatsRU(String cheatsRU) {
        this.cheatsRU = cheatsRU;
    }

    public String getCheatsEN() {
        return cheatsEN;
    }

    public void setCheatsEN(String cheatsEN) {
        this.cheatsEN = cheatsEN;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(yearOfRelease);
        out.writeInt(isFavorite ? 1 : 0);
        out.writeString(cheatsRU);
        out.writeString(cheatsEN);
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}
