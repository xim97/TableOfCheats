package efimovich.bsu.by.tableofcheats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle(R.string.add);
    }

    public void onClickAddCheatsButton(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.add_cheats_dialog_message)
                .setTitle(R.string.add);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                EditText nameEdit = findViewById(R.id.nameEdit);
                EditText yearEdit = findViewById(R.id.yearEdit);
                EditText cheatsEdit = findViewById(R.id.cheatsEdit);
                if (!checkEdits(nameEdit, yearEdit, cheatsEdit)){
                    Toast toast = Toast.makeText(getBaseContext(), R.string.adding_cheats, Toast.LENGTH_SHORT);
                    toast.show();
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

    public boolean checkEdits(EditText nameEdit, EditText yearEdit, EditText cheatsEdit){
        return nameEdit.getText().toString().trim().isEmpty()
                || yearEdit.getText().toString().trim().isEmpty()
                || cheatsEdit.getText().toString().trim().isEmpty();
    }

    public void onClickMusicButton(View view) {
        Intent intent = new Intent(this, MusicService.class);
        if (view.getId() == R.id.musicButtonStart){
            startService(intent);
        }
        else{
            stopService(intent);
        }
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
