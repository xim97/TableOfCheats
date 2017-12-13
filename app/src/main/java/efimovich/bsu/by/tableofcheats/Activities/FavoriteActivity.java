package efimovich.bsu.by.tableofcheats.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import efimovich.bsu.by.tableofcheats.Activities.CheatsActivity;
import efimovich.bsu.by.tableofcheats.Activities.MainActivity;
import efimovich.bsu.by.tableofcheats.Game;
import efimovich.bsu.by.tableofcheats.GameAdapter;
import efimovich.bsu.by.tableofcheats.R;

public class FavoriteActivity extends AppCompatActivity {
    private ArrayList<Game> favoriteGames = new ArrayList<>();
    private ListView favoriteGameList;
    private Context context;
    final String LOG_TAG = "Favorite activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "on create");
        context = this;
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.favorites);
        setContentView(R.layout.activity_favorite);
        if (savedInstanceState == null || !savedInstanceState.containsKey("favoriteGames")) {
            favoriteGames = getIntent().getParcelableArrayListExtra("favoriteGamesFromMainActivity");
        } else {
            favoriteGames = savedInstanceState.getParcelableArrayList("favoriteGames");
        }
        setGameAdapter();
    }

    private void setGameAdapter() {
        Log.d(LOG_TAG, "set game adapter");
        favoriteGameList = findViewById(R.id.favoriteGamesList);
        GameAdapter adapter = new GameAdapter(this, R.layout.list_of_games_item, favoriteGames);
        favoriteGameList.setAdapter(adapter);
        favoriteGameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(context, CheatsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("game", favoriteGames.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(LOG_TAG, "on save instance state");
        outState.putParcelableArrayList("favoriteGames", favoriteGames);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "on restore instance state");
        super.onRestoreInstanceState(savedInstanceState);
        favoriteGames = savedInstanceState.getParcelableArrayList("favoriteGames");
    }

    public void onClickGamesButton(View view) {
        Log.d(LOG_TAG, "on click games button");
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}
