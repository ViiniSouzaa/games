package br.edu.utfpr.danielproenca.games.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import br.edu.utfpr.danielproenca.games.R;
import br.edu.utfpr.danielproenca.games.model.RegisterPubg;

public class Menu extends AppCompatActivity {

    private static final String ARQUIVO = "br.edu.utfpr.danielproenca.games;.sharedpreferences.PREFERENCE_COLOR";
    private static final String COR = "COR";

    private int option = Color.BLUE;

    private ArrayList<RegisterPubg> registerPubgs = new ArrayList<>();
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        layout = findViewById(R.id.MenuMain);

        readPreferencesColors();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void readPreferencesColors(){
        SharedPreferences shared = getSharedPreferences (ARQUIVO,
                                                        Context.MODE_PRIVATE);
        option = shared.getInt(COR, option);
        alterColor();
    }

    private void alterColor(){
        layout.setBackgroundColor(option);
    }

    private void savePreferencesColors(int novoValor){
        SharedPreferences shared = getSharedPreferences(ARQUIVO,
                                                        Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();
        editor.putInt(COR, novoValor);
        editor.commit();
        option = novoValor;
        alterColor();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item;

        switch(option){
            case Color.WHITE:
                item = menu.findViewById(R.id.menuItemWhite);
                break;

            case Color.BLUE:
                item = menu.findViewById(R.id.menuItemBlue);
                break;

            case Color.RED:
                item = menu.findViewById(R.id.menuItemRed);
                break;

            default:
                return false;
        }

        item.setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);

        switch(item.getItemId()){
            case R.id.menuItemWhite:
                savePreferencesColors(Color.WHITE);
                return true;

            case R.id.menuItemBlue:
                savePreferencesColors(Color.BLUE);
                return true;

            case R.id.menuItemRed:
                savePreferencesColors(Color.RED);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void imageViewAdd(View view){
        Intent intent = new Intent(this,
                ChooseGames.class);
        intent.putExtra("acao", 1);
        startActivity(intent);
    }

    public void imageViewInfo(View view){
        Intent intent = new Intent(this,
                About.class);
        startActivity(intent);
    }

    public void imageViewSearch(View view) {
        Intent intent = new Intent(this,
                ChooseGamesList.class);
        intent.putExtra("acao", 2);
        startActivity(intent);
    }

    public void about (View view){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

}
