package efimovich.bsu.by.tableofcheats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CheatsActivity extends AppCompatActivity {

    private void setTextView(int resource, String text){
        TextView textView = findViewById(resource);
        textView.setText(text);
    }

    private void setCheatsView(Game currentGame){
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheats);
        getSupportActionBar().setTitle(R.string.cheats);
        Game currentGame = getIntent().getParcelableExtra("game");

        setTextView(R.id.nameView, currentGame.getName());
        setTextView(R.id.yearView, currentGame.getYearOfReleaseByString());
        setCheatsView(currentGame);
    }
}
