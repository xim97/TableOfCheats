package efimovich.bsu.by.tableofcheats;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameAdapter extends ArrayAdapter<Game> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Game> gameList;

    GameAdapter(Context context, int resource, ArrayList<Game> games) {
        super(context, resource, games);
        this.gameList = games;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Game game = gameList.get(position);

        viewHolder.nameView.setText(game.getName());
        viewHolder.yearView.setText(game.getYearOfReleaseByString());
        viewHolder.favoriteCheckBox.setChecked(game.isFavorite());

        viewHolder.favoriteCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!viewHolder.favoriteCheckBox.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setMessage(R.string.remove_favorite_dialog_message)
                            .setTitle(R.string.remove_favorite_dialog_title);

                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            game.setFavorite(!game.isFavorite());
                            Toast toast = Toast.makeText(getContext(), game.getName() + " " +
                                    getContext().getString(R.string.unmarked_as_favorite), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            viewHolder.favoriteCheckBox.setChecked(true);
                            Toast toast = Toast.makeText(getContext(), game.getName() + " " +
                                    getContext().getString(R.string.marked_as_favorite), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    game.setFavorite(!game.isFavorite());
                    Toast toast = Toast.makeText(getContext(), game.getName() + " " +
                            getContext().getString(R.string.marked_as_favorite), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {
        final CheckBox favoriteCheckBox;
        final TextView nameView, yearView;

        ViewHolder(View view) {
            favoriteCheckBox = view.findViewById(R.id.favoriteCheckBox);
            nameView = view.findViewById(R.id.nameView);
            yearView = view.findViewById(R.id.yearView);
        }
    }
}
