package task_11_filesparser;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Processor {
    private Map<String, Content> userNamesAndCorrespondingContent = new HashMap<>();

    void startParsing() {
        FileReaderFromDisk fileReaderFromDisk = new FileReaderFromDisk();
        fileReaderFromDisk.readFilesFromDisk();
        readContentFromFiles(fileReaderFromDisk);
        FileWriterToDisk fileWriterToDisk = new FileWriterToDisk();
        fileWriterToDisk.writeContentToFile(userNamesAndCorrespondingContent);
    }

    private synchronized void readContentFromFiles(FileReaderFromDisk fileReaderFromDisk) {
        List<File> listOfFiles = fileReaderFromDisk.getListOfFiles();
        for (File file : listOfFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                String lineFromFile = reader.readLine();
                String[] splitLine;
                while ((lineFromFile = reader.readLine()) != null) {
                    splitLine = lineFromFile.split(";");
                    saveContentFromFiles(splitLine[3], splitLine[1], Long.parseLong(splitLine[2]));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void saveContentFromFiles(String userName, String url, long time) {
        if (userNamesAndCorrespondingContent.containsKey(userName)) {
            Content content = userNamesAndCorrespondingContent.get(userName);
            if (content.getUrlAndCorrespondingTime().containsKey(url)) {
                content.updateTimeForCorrespondingUrl(url, time);
            } else {
                content.updateUrlAndTimeInfoForCorrespondingUsername(url, time);
            }
        } else {
            Map<String, Long> newUrlAndTime = new HashMap<>();
            newUrlAndTime.put(url, time);
            userNamesAndCorrespondingContent.put(userName, new Content(newUrlAndTime));
        }
    }
}
