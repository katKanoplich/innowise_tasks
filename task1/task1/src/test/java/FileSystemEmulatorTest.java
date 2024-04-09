import org.example.FileSystemEmulator;
import org.example.model.File;
import org.example.model.Folder;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemEmulatorTest {

    @Test
    void testBuildFileSystem() {
        FileSystemEmulator fileSystemEmulator = new FileSystemEmulator();
        fileSystemEmulator.setRoot(new Folder("root"));;

        fileSystemEmulator.buildFileSystem("folder1/folder2");
        Folder folder1 = fileSystemEmulator.findFolder(fileSystemEmulator.getRoot(), "folder1");
        Folder folder2 = fileSystemEmulator.findFolder(folder1, "folder2");

        assertNotNull(folder2);
    }

    @Test
    void testIsFile() {
        FileSystemEmulator fileSystemEmulator = new FileSystemEmulator();
        assertTrue(fileSystemEmulator.isFile("file.txt"));
        assertFalse(fileSystemEmulator.isFile("folder"));
    }


    @Test
    void testPrintFileSystem() {

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

        String expectedOutput = "root" + System.lineSeparator() +
                "folder1" + System.lineSeparator() +
                "  folder2" + System.lineSeparator() +
                "  file2.txt" + System.lineSeparator() +
                "file1.txt" + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }
    @Test
    void testFindFolder() {

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
