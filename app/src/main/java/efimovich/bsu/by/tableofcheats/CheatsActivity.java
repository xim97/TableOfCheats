package efimovich.bsu.by.tableofcheats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CheatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheats);
        getSupportActionBar().setTitle(R.string.cheats);
        Game currentGame = getIntent().getParcelableExtra("game");

        TextView textView = findViewById(R.id.nameView);
        textView.setText(currentGame.getName());

        textView = findViewById(R.id.yearView);
        textView.setText(currentGame.getYearOfReleaseByString());

        textView = findViewById(R.id.cheatsView);
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
}
