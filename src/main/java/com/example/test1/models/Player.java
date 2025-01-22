package com.example.test1.models;

public class Player {
    private String name;
    private int health;
    private int score;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void dealDamage(int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    public void heal(int amount) {
        this.health = Math.min(100, this.health + amount);
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }
}
