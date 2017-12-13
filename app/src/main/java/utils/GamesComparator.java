package utils;

import java.util.Comparator;

import efimovich.bsu.by.tableofcheats.Game;

public class GamesComparator implements Comparator<Game> {

    public int compare(Game game1, Game game2){
        if (game1.getName().length() == game2.getName().length()){
            return game1.getName().compareToIgnoreCase(game2.getName());
        } else {
            return game1.getName().length() - game2.getName().length();
        }
    }
}
