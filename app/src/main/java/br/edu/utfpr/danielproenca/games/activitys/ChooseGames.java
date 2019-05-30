package br.edu.utfpr.danielproenca.games.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.edu.utfpr.danielproenca.games.R;

public class ChooseGames extends AppCompatActivity {

    private RadioGroup radioGroupChooseGames;
    Intent intent;
    int acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_games);
        intent = getIntent();
        acao = intent.getIntExtra("acao", 0);

        radioGroupChooseGames = findViewById(R.id.radioGroupChooseGames);

    }

    public void chooseGames(View view){
        switch (radioGroupChooseGames.getCheckedRadioButtonId()){
            case R.id.radioButtonSoccer:
                if(acao == 1){
                    Intent intent = new Intent(this,
                            Soccer.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(this,
                            ShowDataSoccer.class);
                    startActivity(intent);
                }

                break;

            case R.id.radioButtonPubg:
                if(acao == 1) {
                    Intent intent = new Intent(this,
                            Pubg.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(this,
                            ShowDataPubg.class);
                    startActivity(intent);
                }

                break;

            default:
                Toast.makeText(this,
                        getString(R.string.error_choosegame),
                        Toast.LENGTH_LONG).show();

        }
    }
}
