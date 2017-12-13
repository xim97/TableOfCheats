package efimovich.bsu.by.tableofcheats.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import efimovich.bsu.by.tableofcheats.Game;
import efimovich.bsu.by.tableofcheats.GameAdapter;
import efimovich.bsu.by.tableofcheats.Services.MusicService;
import efimovich.bsu.by.tableofcheats.R;
import utils.GamesCollection;
import utils.GamesComparator;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<Game> games = new ArrayList<>();
    private ListView gamesList;
    private Intent musicIntent;
    private static final int REQUEST_ACCESS_TYPE = 1;
    private Context context;
    static final String GAME = "";
    final String LOG_TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.games);
        musicIntent = new Intent(this, MusicService.class);

        if (savedInstanceState != null && savedInstanceState.containsKey("games")) {
            games = savedInstanceState.getParcelableArrayList("games");
        } else {
            createGames();
        }
        Collections.sort(games, new GamesComparator());

        setGameAdapter();
    }

    public void setGameAdapter() {
        Log.d(LOG_TAG, "setting game adapter");
        gamesList = findViewById(R.id.gamesList);
        GameAdapter adapter = new GameAdapter(this, R.layout.list_of_games_item, games);
        gamesList.setAdapter(adapter);
        gamesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(context, CheatsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("game", games.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Collections.sort(games, new GamesComparator());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ACCESS_TYPE) {
            if (resultCode == RESULT_OK) {
                Game addedGame = data.getParcelableExtra(GAME);
                games.add(addedGame);
                Log.d(LOG_TAG, "Getting new game");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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
        Collections.sort(games, new GamesComparator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.music_start: {
                startService(musicIntent);
                Log.d(LOG_TAG, "starting music service");
                return true;
            }
            case R.id.music_stop: {
                stopService(musicIntent);
                Log.d(LOG_TAG, "stopping music service");
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void createGames() {
        Log.d(LOG_TAG, "Creating games for first start");
        for (int i = 0; i < 20; i++) {
            games.add(new Game("game" + i, i * 1000, false, "фывафывафыва" + i, "asdfasdf" + i));
        }
    }

    public void onClickAddButton(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(intent, REQUEST_ACCESS_TYPE);
    }

    public void onClickGamesButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void onClickFavoritesButton(View view) {
        ArrayList<Game> favoriteGamesToFavoriteActivity = GamesCollection.getFavoriteGames(games);
        if (favoriteGamesToFavoriteActivity.size() != 0) {
            Intent intent = new Intent(this, FavoriteActivity.class);
            intent.putParcelableArrayListExtra("favoriteGamesFromMainActivity", favoriteGamesToFavoriteActivity);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, R.string.no_favorite_games, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
