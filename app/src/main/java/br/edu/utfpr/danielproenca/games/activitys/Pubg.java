package br.edu.utfpr.danielproenca.games.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.utfpr.danielproenca.games.R;
import br.edu.utfpr.danielproenca.games.dao.GamesDatabase;
import br.edu.utfpr.danielproenca.games.model.RegisterPubg;

public class Pubg extends AppCompatActivity {

    private ArrayList<RegisterPubg> registerPubgs = new ArrayList<>();
    private EditText editTextNickName;
    private EditText editTextLevel;
    private EditText editTextRounds;
    private EditText editTextSlaughter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubg);

        editTextNickName = findViewById(R.id.editTextNickName);
        editTextLevel = findViewById(R.id.editTextLevel);
        editTextRounds = findViewById(R.id.editTextRounds);
        editTextSlaughter = findViewById(R.id.editTextSlaughter);
    }

    public void clearFields(View view){
        editTextNickName.setText(null);
        editTextLevel.setText(null);
        editTextRounds.setText(null);
        editTextSlaughter.setText(null);

        editTextNickName.requestFocus();
        Toast.makeText(this,
                R.string.message_clear,
                Toast.LENGTH_LONG).show();
    }

    public void showRegisterPubg(View view) {
        Intent intent = new Intent(this,
                ShowDataPubg.class);
        intent.putExtra("Registers", registerPubgs);
        startActivity(intent);
    }

    public void sendFields(View view){
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
        GamesDatabase.getDatabase(getApplicationContext()).daoPubg().insert(registerPubg);

        editTextNickName.setText(null);
        editTextLevel.setText(null);
        editTextRounds.setText(null);
        editTextSlaughter.setText(null);

        editTextNickName.requestFocus();

        Toast.makeText(this,
                nickname + "\n" + level + "\n" + rounds  + "\n" + slaughter,
                Toast.LENGTH_LONG).show();

    }
}
