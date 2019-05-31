package br.edu.utfpr.danielproenca.games.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.utfpr.danielproenca.games.R;
import br.edu.utfpr.danielproenca.games.dao.GamesDatabase;
import br.edu.utfpr.danielproenca.games.model.RegisterPubg;
import br.edu.utfpr.danielproenca.games.model.RegisterSoccer;

public class ChangeSoccer extends AppCompatActivity {

    private EditText editTextTeam;
    private EditText editTextGoals;
    private EditText editTextRoundsS;
    long id;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_soccer);

        editTextTeam = findViewById(R.id.editTextTeam2);
        editTextGoals = findViewById(R.id.editTextGoals2);
        editTextRoundsS = findViewById(R.id.editTextRoundsS2);
        intent = getIntent();
        showSelected();
    }

    public void showSelected(){
        id = intent.getLongExtra("idSoccer", -1);
        RegisterSoccer registerSoccer = GamesDatabase.getDatabase(getApplicationContext()).daoSoccer().getById(id);

        editTextTeam.setText(registerSoccer.getTeam());
        editTextGoals.setText(String.valueOf(registerSoccer.getGoals()));
        editTextRoundsS.setText(String.valueOf(registerSoccer.getRounds()));
    }

    public void clearFieldsS(View view){
        editTextTeam.setText(null);
        editTextGoals.setText(null);
        editTextRoundsS.setText(null);

        editTextTeam.requestFocus();
        Toast.makeText(this,
                R.string.message_clear,
                Toast.LENGTH_LONG).show();
    }

    public void sendFieldsS(View view){
        String team = editTextTeam.getText().toString();
        int goals = Integer.parseInt(editTextGoals.getText().toString());
        int rounds = Integer.parseInt(editTextRoundsS.getText().toString());

        if(team == null || team.trim().isEmpty()){
            Toast.makeText(this,
                    getString(R.string.error_nickname),
                    Toast.LENGTH_LONG).show();

            editTextTeam.requestFocus();
            return;
        }
        RegisterSoccer registerSoccer = new RegisterSoccer(team, goals, rounds);
        registerSoccer.setId(id);
        GamesDatabase.getDatabase(getApplicationContext()).daoSoccer().update(registerSoccer);

        Toast.makeText(this,
                team + "\n" + goals + "\n" + rounds,
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), ShowDataSoccer.class);
        startActivity(intent);
    }
}
