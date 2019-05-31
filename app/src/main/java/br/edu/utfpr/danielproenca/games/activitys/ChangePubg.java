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

public class ChangePubg extends AppCompatActivity {
    private EditText editTextNickName;
    private EditText editTextLevel;
    private EditText editTextRounds;
    private EditText editTextSlaughter;
    private long id;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pubg);
        intent = getIntent();

        editTextNickName = findViewById(R.id.editTextNickName2);
        editTextLevel = findViewById(R.id.editTextLevel2);
        editTextRounds = findViewById(R.id.editTextRounds2);
        editTextSlaughter = findViewById(R.id.editTextSlaughter2);

        showSelected();
    }

    public void showSelected(){
        id = intent.getLongExtra("idPubg", -1);
        RegisterPubg registerPubg = GamesDatabase.getDatabase(getApplicationContext()).daoPubg().getById(id);
        if(registerPubg != null){
            editTextNickName.setText(registerPubg.getNickname());
            editTextLevel.setText(String.valueOf(registerPubg.getLevel()));
            editTextRounds.setText(String.valueOf(registerPubg.getRounds()));
            editTextSlaughter.setText(String.valueOf(registerPubg.getSlaughter()));
        }
    }

    public void clearFields2(View view){
        editTextNickName.setText(null);
        editTextLevel.setText(null);
        editTextRounds.setText(null);
        editTextSlaughter.setText(null);

        editTextNickName.requestFocus();
        Toast.makeText(this,
                R.string.message_clear,
                Toast.LENGTH_LONG).show();
    }

    public void sendFields2(View view){
        String nickname = editTextNickName.getText().toString();
        int level = Integer.parseInt(editTextLevel.getText().toString());
        int rounds = Integer.parseInt(editTextRounds.getText().toString());
        int slaughter = Integer.parseInt(editTextSlaughter.getText().toString());

        if(nickname == null || nickname.trim().isEmpty()){
            Toast.makeText(this,
                    getString(R.string.error_nickname),
                    Toast.LENGTH_LONG).show();

            editTextNickName.requestFocus();
            return;
        }

        RegisterPubg registerPubg = new RegisterPubg(nickname, level, rounds, slaughter);
        registerPubg.setId(id);
        GamesDatabase.getDatabase(getApplicationContext()).daoPubg().update(registerPubg);

        Toast.makeText(this,
                nickname + "\n" + level + "\n" + rounds  + "\n" + slaughter,
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), ShowDataPubg.class);
        startActivity(intent);

    }
}
