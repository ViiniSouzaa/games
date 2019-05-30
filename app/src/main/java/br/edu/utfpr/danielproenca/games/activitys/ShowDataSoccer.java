package br.edu.utfpr.danielproenca.games.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import br.edu.utfpr.danielproenca.games.model.RegisterSoccer;

public class ShowDataSoccer extends AppCompatActivity {

    private ListView listViewDataSoccer;
    List<RegisterSoccer> registerSoccers = new ArrayList<>();
    ArrayAdapter<RegisterSoccer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_soccer);

        listViewDataSoccer = findViewById(R.id.ListViewDataSoccer);

        registerSoccers = GamesDatabase.getDatabase(getApplicationContext()).daoSoccer().getAll();

        ArrayAdapter<RegisterSoccer> adapter =
                new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1,
                        registerSoccers);

        listViewDataSoccer.setAdapter(adapter);
        registerForContextMenu(listViewDataSoccer);
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
        registerSoccers.remove(position);
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
