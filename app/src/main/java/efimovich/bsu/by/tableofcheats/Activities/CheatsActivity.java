package efimovich.bsu.by.tableofcheats.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import efimovich.bsu.by.tableofcheats.Game;
import efimovich.bsu.by.tableofcheats.R;

public class CheatsActivity extends AppCompatActivity {
    final String LOG_TAG = "Cheats activity";

    private void setTextView(int resource, String text){
        Log.d(LOG_TAG, "set text view");
        TextView textView = findViewById(resource);
        textView.setText(text);
    }

    private void setCheatsView(Game currentGame){
        Log.d(LOG_TAG, "set cheats view");
        TextView textView = findViewById(R.id.cheatsView);
        switch (getResources().getConfiguration().locale.getLanguage()) {
            case "ru": {
                textView.setText(currentGame.getCheatsRU());
                break;
            }
            case "en": {
                textView.setText(currentGame.getCheatsEN());
                break;
            }
            default: {
                textView.setText(currentGame.getCheatsEN());
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheats);
        getSupportActionBar().setTitle(R.string.cheats);
        Game currentGame = getIntent().getParcelableExtra("game");

        setTextView(R.id.nameView, currentGame.getName());
        setTextView(R.id.yearView, currentGame.getYearOfReleaseByString());
        setCheatsView(currentGame);
    }
}
