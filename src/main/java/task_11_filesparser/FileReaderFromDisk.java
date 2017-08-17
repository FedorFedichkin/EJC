package task_11_filesparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The only purpose of this class is reading names of *.csv files and storing them in listOfFiles.
 */
public class FileReaderFromDisk {
    private List<File> listOfFiles = new ArrayList<>();

    List<File> getListOfFiles() {
        return listOfFiles;
    }

    /**
     * This method reads all files from directory "filePath" and saves names of files to listOfFiles field.
     */
    void readFilesFromDisk() {
        String filePath = "EJC//src//main//java//task_11_filesparser//FolderWithCsvFiles";
        File folderWithData = new File(filePath);
        if (folderWithData.isDirectory()) {
            for (File file : folderWithData.listFiles()) {
                if (file.exists() && file.canRead()) {
                    listOfFiles.add(file);
                }
            }
        }
    }
}
