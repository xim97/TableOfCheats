package efimovich.bsu.by.tableofcheats;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    private ArrayList<Game> favoriteGames = new ArrayList<>();
    private ListView favoriteGameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.favorites);
        setContentView(R.layout.activity_favorite);
        Log.d("TAG", "creating Favorite activity");
        favoriteGames = getIntent().getParcelableArrayListExtra("favoriteGamesFromMainActivity");
        if (savedInstanceState == null || !savedInstanceState.containsKey("favoriteGames")) {

        } else {
            favoriteGames = savedInstanceState.getParcelableArrayList("favoriteGames");
        }

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
        outState.putParcelableArrayList("favoriteGames", favoriteGames);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        favoriteGames = savedInstanceState.getParcelableArrayList("favoriteGames");
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
