package org.example.model;

import java.util.ArrayList;


public class Folder implements IFileSystemItem {
    private String name;
    private ArrayList<IFileSystemItem> components;

    public Folder(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<IFileSystemItem> getComponents() {
        return components;
    }
}