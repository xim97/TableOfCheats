package utils;

import java.util.ArrayList;

import efimovich.bsu.by.tableofcheats.Game;

public class GamesCollection {

    public static ArrayList<Game> getFavoriteGames(ArrayList<Game> games){
        ArrayList<Game> ans = new ArrayList<>();
        for(int i = 0; i < games.size(); i++){
            if (games.get(i).isFavorite()){
                ans.add(games.get(i));
            }
        }
        return ans;
    }
}
