package com.corejsf;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
