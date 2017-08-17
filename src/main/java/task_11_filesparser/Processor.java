package task_11_filesparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 */
public class Processor {
    private Map<String, Content> userNamesAndCorrespondingContent = new ConcurrentSkipListMap<>();
    private List<Thread> listOfThreads;

    void startParsing() {
        FileReaderFromDisk fileReaderFromDisk = new FileReaderFromDisk();
        fileReaderFromDisk.readFilesFromDisk();

        initialiseThreads(fileReaderFromDisk.getListOfFiles());
        processThreads();
        FileWriterToDisk fileWriterToDisk = new FileWriterToDisk();
        fileWriterToDisk.writeContentToFile(userNamesAndCorrespondingContent);
    }

    private void initialiseThreads(List<File> listOfCsvFiles) {
        int numberOfThreads = 10;
        listOfThreads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Runnable runnable = new CsvParser(userNamesAndCorrespondingContent, listOfCsvFiles.get(i));
            Thread thread = new Thread(runnable);
            listOfThreads.add(thread);
        }
    }

    private void processThreads() {
        for (Thread thread : listOfThreads) {
            thread.start();
        }
        for (Thread thread : listOfThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
