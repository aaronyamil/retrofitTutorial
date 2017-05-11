package com.aaronyamil.retrofittutorial.model;

import java.util.List;

/**
 * Created by AaronYamil on 4/8/2017.
 */

public class RandomUser {

    private List<User> results;
    private Info info;

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
