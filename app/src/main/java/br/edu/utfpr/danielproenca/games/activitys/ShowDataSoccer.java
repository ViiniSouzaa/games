package br.edu.utfpr.danielproenca.games.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.danielproenca.games.R;
import br.edu.utfpr.danielproenca.games.dao.GamesDatabase;
import br.edu.utfpr.danielproenca.games.model.RegisterPubg;
import br.edu.utfpr.danielproenca.games.model.RegisterSoccer;

public class ShowDataSoccer extends AppCompatActivity {

    private ListView listViewDataSoccer;
    List<RegisterSoccer> registerSoccers = new ArrayList<>();
    ArrayAdapter<RegisterSoccer> adapter;

    private ActionMode actionMode;
    private int positionSelected = -1;
    private View viewSelected;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menushow, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.MenuItemChange:
                    changeSelected();
                    mode.finish();
                    return true;

                case R.id.MenuItemDelete:
                    deleteSelected();
                    mode.finish();
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (viewSelected != null) {
                viewSelected.setBackgroundColor(Color.TRANSPARENT);
            }
            actionMode = null;
            viewSelected = null;

            listViewDataSoccer.setEnabled(true);
        }
    };

    private void deleteSelected() {

        AlertDialog.Builder confirm = new AlertDialog.Builder(this);
        confirm.setMessage(getString(R.string.confirmDelete));
        confirm.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RegisterSoccer s = GamesDatabase.getDatabase(getApplicationContext()).daoSoccer().getById(registerSoccers.get(positionSelected).getId());
                GamesDatabase.getDatabase(getApplicationContext()).daoSoccer().delete(s);
                populaLista();
            }
        });
        confirm.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        confirm.show();
    }

    private void changeSelected() {
        Intent intent = new Intent(getApplicationContext(), ChangeSoccer.class);
        intent.putExtra("idSoccer", registerSoccers.get(positionSelected).getId());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_soccer);

        listViewDataSoccer = findViewById(R.id.ListViewDataSoccer);
        listViewDataSoccer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionSelected = position;
                changeSelected();
            }
        });

        listViewDataSoccer.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listViewDataSoccer.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    return false;
                }
                positionSelected = position;
                view.setBackgroundColor(Color.LTGRAY);
                viewSelected = view;
                listViewDataSoccer.setEnabled(true);
                actionMode = startSupportActionMode(mActionModeCallback);

                return true;
            }
        });
        populaLista();
    }

    private void populaLista() {
        registerSoccers = GamesDatabase.getDatabase(getApplicationContext()).daoSoccer().getAll();

        ArrayAdapter<RegisterPubg> adapter =
                new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1,
                        registerSoccers);

        listViewDataSoccer.setAdapter(adapter);
        registerForContextMenu(listViewDataSoccer);
    }
}