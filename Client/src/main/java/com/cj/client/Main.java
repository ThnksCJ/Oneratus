package com.cj.client;


public class Main {
    private final Loading client = new Loading();

    public void start() {
        Loading.load();
    }

    public void reload() {
        Loading.unload();
    }
}

