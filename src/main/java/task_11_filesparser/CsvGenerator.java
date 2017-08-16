package task_11_filesparser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 */
public class CsvGenerator {
    private final String[] webSiteNames = new String[]{"https://habrahabr.ru/", "https://stackoverflow.com/",
            "https://www.epam.com/", "https://www.facebook.com/", "https://www.youtube.com/", "https://www.yandex.ru/",
            "https://www.google.com/", "http://livejournal.com/", "https://postnauka.ru", "https://vk.com"};

    private final String[] userNames = new String[]{"Henri", "Edgar", "Claude", "Pierre-Auguste", "Edouard", "Paul",
            "Camille", "Gustave", "Vincent", "Pablo"};

    void generateCsvFiles() {
        String filePath = "EJC//src//main//java//task_11_filesparser//FolderWithCsvFiles//";
        Random random = new Random();
        int numberOfFiles = 10;
        for (int i = 1; i < numberOfFiles + 1; i++) {
            String newCsvFileName = filePath + "FileName" + i + ".csv";
            File folderWithCsvFiles = new File(filePath);
            File newCsvFile = new File(newCsvFileName);
            if (!folderWithCsvFiles.exists()) {
                folderWithCsvFiles.mkdir();
            }
            try {
                newCsvFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (FileWriter writer = new FileWriter(newCsvFileName)) {
                int numberOfLinesInFile = random.nextInt(999)+1;
                for (int j = 0; j < numberOfLinesInFile; j++) {
                    int id = random.nextInt(Integer.MAX_VALUE);
                    int webSiteNameIndex = random.nextInt(10);
                    long timeSpentOnWebSite = random.nextInt(Integer.MAX_VALUE);
                    int userNameIndex = random.nextInt(10);
                    String currentUserName = userNames[userNameIndex];
                    String currentWebSite = webSiteNames[webSiteNameIndex];
                    String currentLine = id + ";" + currentWebSite + ";"
                            + timeSpentOnWebSite + ";" + currentUserName + "\r\n";
                    writer.write(currentLine);
                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
