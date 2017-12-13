package efimovich.bsu.by.tableofcheats.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import efimovich.bsu.by.tableofcheats.Game;
import efimovich.bsu.by.tableofcheats.R;

public class AddActivity extends AppCompatActivity {
    final String LOG_TAG = "Add Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle(R.string.add);
    }

    public void onClickAddCheatsButton(View view) {
        Log.d(LOG_TAG, "on click add cheats button");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.add_cheats_dialog_message)
                .setTitle(R.string.add);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d(LOG_TAG, "trying to add new game");
                EditText nameEdit = findViewById(R.id.nameEdit);
                EditText yearEdit = findViewById(R.id.yearEdit);
                EditText cheatsEdit = findViewById(R.id.cheatsEdit);
                if (!isEmptyInput(nameEdit, yearEdit, cheatsEdit)) {
                    if (isValideInput(yearEdit)){
                        sendGame(nameEdit, yearEdit, cheatsEdit);
                        Toast toast = Toast.makeText(getBaseContext(), R.string.adding_cheats, Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getBaseContext(), R.string.unvalide_game, Toast.LENGTH_SHORT);
                        toast.show();
                    }

                } else {
                    Toast toast = Toast.makeText(getBaseContext(), R.string.error_input_cheats, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void sendGame(EditText nameEdit, EditText yearEdit, EditText cheatsEdit) {
        String name = nameEdit.getText().toString().trim();
        int year = Integer.valueOf(yearEdit.getText().toString().trim());
        String cheats = cheatsEdit.getText().toString().trim();
        Game game = new Game(name, year, cheats, getResources().getConfiguration().locale.getLanguage());

        Log.d(LOG_TAG, "sending game to main activity");
        Intent data = new Intent();
        data.putExtra(MainActivity.GAME, game);
        setResult(RESULT_OK, data);
        finish();
    }

    public boolean isEmptyInput(EditText nameEdit, EditText yearEdit, EditText cheatsEdit) {
        return nameEdit.getText().toString().trim().isEmpty()
                || yearEdit.getText().toString().trim().isEmpty()
                || cheatsEdit.getText().toString().trim().isEmpty();
    }

    public boolean isValideInput(EditText yearEdit) {
        try {
            Integer year = Integer.parseInt(yearEdit.getText().toString().trim());
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    public void onClickAddButton(View view) {
        Log.d(LOG_TAG, "on click add button");
        Intent intent = new Intent(this, AddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void onClickGamesButton(View view) {
        Log.d(LOG_TAG, "on click games button");
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}
