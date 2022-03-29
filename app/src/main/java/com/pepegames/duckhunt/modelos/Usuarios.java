package com.pepegames.duckhunt.modelos;

public class Usuarios {
    private String nick;
    private int zombies;

    public Usuarios() {
    }

    public Usuarios(String nick, int zombies) {
        this.nick = nick;
        this.zombies = zombies;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getZombies() {
        return zombies;
    }

    public void setZombies(int zombies) {
        this.zombies = zombies;
    }
}
