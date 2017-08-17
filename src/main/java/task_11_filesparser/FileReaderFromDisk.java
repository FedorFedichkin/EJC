package task_11_filesparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileReaderFromDisk {
    private List<File> listOfFiles = new ArrayList<>();

    List<File> getListOfFiles() {
        return listOfFiles;
    }

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
