package br.edu.utfpr.danielproenca.games.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "soccer")
public class RegisterSoccer {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String team;
    private int goals;
    private int rounds;

    public RegisterSoccer(String team, int goals, int rounds){
        setTeam(team);
        setGoalsl(goals);
        setRounds(rounds);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String nickname) {
        this.team = nickname;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoalsl(int goals) {
        this.goals = goals;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }


    @Override
    public String toString() {
        return "Name: " + team + "\n"
                + "Level: " + goals + "\n"
                + "Rounds: " + rounds;
    }
}
