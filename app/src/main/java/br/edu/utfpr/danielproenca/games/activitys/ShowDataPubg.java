package br.edu.utfpr.danielproenca.games.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
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

public class ShowDataPubg extends AppCompatActivity {

    private ListView listViewDataPubg;
    List<RegisterPubg> registerPubgs = new ArrayList<>();
    ArrayAdapter<RegisterPubg> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_pubg);

        listViewDataPubg = findViewById(R.id.ListViewDataPubg);

        registerPubgs = GamesDatabase.getDatabase(getApplicationContext()).daoPubg().getAll();

        ArrayAdapter<RegisterPubg> adapter =
                new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1,
                        registerPubgs);

        listViewDataPubg.setAdapter(adapter);
        registerForContextMenu(listViewDataPubg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menushow) {
        getMenuInflater().inflate(R.menu.menushow, menushow);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.MenuItemDelete:
                Toast.makeText(this,
                        getString(R.string.development),
                        Toast.LENGTH_LONG).show();
                return true;

            case R.id.MenuItemChange:
                Toast.makeText(this,
                        getString(R.string.development),
                        Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void delete(int position){
        registerPubgs.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menushow, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menushow, view, menuInfo);

        getMenuInflater().inflate(R.menu.menushow, menushow);
    }

    @Override
    public boolean onContextItemSelected(MenuItem data) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) data.getMenuInfo();

        switch (data.getItemId()) {

            case R.id.MenuItemChange:
                Toast.makeText(this,
                        getString(R.string.development),
                        Toast.LENGTH_LONG).show();
                return true;

            case R.id.MenuItemDelete :
                //delete(info.position);
                Toast.makeText(this,
                        getString(R.string.development),
                        Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onContextItemSelected(data);
        }
    }
}
