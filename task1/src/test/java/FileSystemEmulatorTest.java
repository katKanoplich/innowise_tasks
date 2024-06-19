
import org.example.FileSystemEmulator;
import org.example.model.File;
import org.example.model.Folder;
import org.example.model.IFileSystemItem;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemEmulatorTest {

    @Test
    public void testBuildFileSystem() {
        FileSystemEmulator fileSystemEmulator = new FileSystemEmulator();
        fileSystemEmulator.root = new Folder("root");

        fileSystemEmulator.buildFileSystem("folder1/folder2");
        Folder folder1 = fileSystemEmulator.findFolder(fileSystemEmulator.root, "folder1");
        Folder folder2 = fileSystemEmulator.findFolder(folder1, "folder2");

        assertTrue(folder1 != null && folder2 != null);
    }

    @Test
    public void testIsFile() {
        FileSystemEmulator fileSystemEmulator = new FileSystemEmulator();
        assertTrue(fileSystemEmulator.isFile("file.txt"));
        assertTrue(!fileSystemEmulator.isFile("folder"));
    }


    @Test
    public void testPrintFileSystem() {

        Folder root = new Folder("root");
        Folder folder1 = new Folder("folder1");
        Folder folder2 = new Folder("folder2");
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");

        root.getComponents().add(folder1);
        root.getComponents().add(file1);
        folder1.getComponents().add(folder2);
        folder1.getComponents().add(file2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FileSystemEmulator fileSystemEmulator = new FileSystemEmulator();
        fileSystemEmulator.printFileSystem(root, 0);

        String expectedOutput = "root\n" +
                "folder1\n" +
                "  folder2\n" +
                "  file2.txt\n" +
                "file1.txt\n";

        String normalizedExpectedOutput = expectedOutput.replace("\r", "");

        assertEquals(normalizedExpectedOutput, outContent.toString());
    }
    @Test
    public void testFindFolder() {

        Folder root = new Folder("root");
        Folder folder1 = new Folder("folder1");
        Folder folder2 = new Folder("folder2");

        root.getComponents().add(folder1);
        root.getComponents().add(folder2);


        FileSystemEmulator fileSystemEmulator = new FileSystemEmulator();

        assertEquals(folder1, fileSystemEmulator.findFolder(root, "folder1"));

        assertNull(fileSystemEmulator.findFolder(root, "nonexistent_folder"));
    }



}
