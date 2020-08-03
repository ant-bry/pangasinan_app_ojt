package com.example.anthonybryanmpagarigan.ph_ojt2.Model;

public class Ranking {
    private String fullName;
    private long score;

    public Ranking() {
    }

    public Ranking(String fullName, long score) {
        this.fullName = fullName;
        this.score = score;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
