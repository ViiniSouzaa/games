package br.edu.utfpr.danielproenca.games.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.edu.utfpr.danielproenca.games.R;

public class ChooseGamesList extends AppCompatActivity {

    private RadioGroup radioGroupChooseGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_games);

        radioGroupChooseGames = findViewById(R.id.radioGroupChooseGamesList);

    }

    public void chooseGames(View view){
        switch (radioGroupChooseGames.getCheckedRadioButtonId()){
            case R.id.radioButtonSoccer:
                    Intent intent = new Intent(this,
                            ShowDataSoccer.class);
                    startActivity(intent);
                break;

            case R.id.radioButtonPubg:
                    Intent intent2 = new Intent(this,
                            ShowDataPubg.class);
                    startActivity(intent2);



                break;

            default:
                Toast.makeText(this,
                        getString(R.string.error_choosegame),
                        Toast.LENGTH_LONG).show();

        }
    }
}
