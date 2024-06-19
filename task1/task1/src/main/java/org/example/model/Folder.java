package org.example.model;

import java.util.ArrayList;
import java.util.List;


public class Folder implements IFileSystemItem {
    private final String name;
    private final ArrayList<IFileSystemItem> components;

    public Folder(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    public String name() {
        return name;
    }

    public List<IFileSystemItem> getComponents() {
        return components;
    }
}