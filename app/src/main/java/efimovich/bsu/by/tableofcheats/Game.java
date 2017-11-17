package efimovich.bsu.by.tableofcheats;

public class Game {
    private String name;
    private int yearOfRelease;
    private boolean isFavorite;
    private String cheats;

    public Game(){
        name = "asdf";
        yearOfRelease = 1000;
        isFavorite = false;
    }

    public Game(String name, int yearOfRelease, boolean isFavorite){
        this.name = name;
        this.yearOfRelease = yearOfRelease;
        this.isFavorite = isFavorite;
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

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheats() {
        return cheats;
    }

    public void setCheats(String cheats) {
        this.cheats = cheats;
    }
}
