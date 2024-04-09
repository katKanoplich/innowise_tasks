package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.model.File;
import org.example.model.Folder;
import org.example.model.IFileSystemItem;

import java.util.Scanner;
import java.util.logging.Logger;

public class FileSystemEmulator {

    @Setter
    @Getter
    private Folder root;

    public void run() {
        root = new Folder("root");
        Scanner scanner = new Scanner(System.in);

        while (true) {


            System.out.print("$> ");
            String input = scanner.nextLine();
            if (input.equals("print")) {
                for (IFileSystemItem subComponent : root.getComponents()) {
                    printFileSystem(subComponent, 1);
                }
            } else if (input.equals("exit")) {
                break;
            } else {
                buildFileSystem(input);
            }
        }

        scanner.close();
    }

    public void buildFileSystem(String path) {
        Folder currentFolder = root;
        String[] components = path.split("/");

        for (int i = 0; i < components.length; i++) {//два файла подряд
            if (isFile(components[i]) && i != components.length - 1) {
                // не знаю, нужно ли что-то сказать пользователю или просто не записывать в дерево?
                return;
            }
        }

        for (int i = 0; i < components.length - 1; i++) {
            String component = components[i];
            if (component.isEmpty()) {
                continue;
            }

            Folder folder = findFolder(currentFolder, component);
            if (folder == null) {
                folder = new Folder(component);
                currentFolder.getComponents().add(folder);
            }

            currentFolder = folder;
        }

        if (isFile(components[components.length - 1])) {
            File file = new File(components[components.length - 1]);
            currentFolder.getComponents().add(file);
        } else {
            Folder folder = new Folder(components[components.length - 1]);
            currentFolder.getComponents().add(folder);
        }
    }

    public boolean isFile(String name) {
        return name.contains(".");
    }

    public Folder findFolder(Folder parentFolder, String folderName) {
        for (IFileSystemItem folder : parentFolder.getComponents()) {
            if (folder.name().equals(folderName)) {
                return (Folder) folder;
            }
        }
        return null;
    }

    public void printFileSystem(IFileSystemItem component, int level) {
        for (int i = 1; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(component.name());

        if (component instanceof Folder folder) {
            for (IFileSystemItem subComponent : folder.getComponents()) {
                printFileSystem(subComponent, level + 1);
            }
        }
    }
}