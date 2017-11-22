package efimovich.bsu.by.tableofcheats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Game> games = new ArrayList<>();
    private ListView gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null && savedInstanceState.containsKey("games")) {
            games = savedInstanceState.getParcelableArrayList("games");
            Toast toast = Toast.makeText(this, "getParcelableArrayList", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            for (int i = 0; i < 100; i++) {
                games.add(new Game("game" + i, i * 1000, i % 2 == 0));
            }
        }

        gameList = findViewById(R.id.gamesList);
        GameAdapter adapter = new GameAdapter(this, R.layout.list_of_games_item, games);
        gameList.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("games", games);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        games = savedInstanceState.getParcelableArrayList("games");
    }

    public void onClickAddButton(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void onClickGamesButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void onClickFavoritesButton(View view) {
        Intent intent = new Intent(this, FavoriteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}
