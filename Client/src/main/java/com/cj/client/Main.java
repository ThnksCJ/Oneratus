package com.cj.client;


public class Main {
    private final Loading loading = new Loading();

    public void start() {
        loading.load();
    }

    public void reload() {
        loading.unload();
    }
}

