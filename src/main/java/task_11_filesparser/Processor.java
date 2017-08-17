package task_11_filesparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * This class manages reading *.csv files, launching 10 parsing threads and writing report.csv file.
 */
public class Processor {
    private Map<String, Content> userNamesAndCorrespondingContent = new ConcurrentSkipListMap<>();
    private List<Thread> listOfThreads;

    /**
     * This method manages reading, processing (parsing) and writing methods.
     */
    void startProcessing() {
        FileReaderFromDisk fileReaderFromDisk = new FileReaderFromDisk();
        fileReaderFromDisk.readFilesFromDisk();
        createThreads(fileReaderFromDisk.getListOfFiles());
        processThreads();
        FileWriterToDisk fileWriterToDisk = new FileWriterToDisk();
        fileWriterToDisk.writeContentToReportFile(userNamesAndCorrespondingContent);
    }

    /**
     * This method creates 10 parsing threads, names them and adds each of them to the listOfThreads.
     */
    private void createThreads(List<File> listOfCsvFiles) {
        int numberOfThreads = 10;
        listOfThreads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Runnable runnable = new CsvParser(userNamesAndCorrespondingContent, listOfCsvFiles.get(i));
            Thread thread = new Thread(runnable);
            thread.setName("Parser thread №" + i);
            listOfThreads.add(thread);
        }
    }

    /**
     * In the method all threads from listOfThreads are launched one by one.
     */
    private void processThreads() {
        for (Thread thread : listOfThreads) {
            thread.start();
            System.out.println(thread.getName() + " was launched.");
        }
        for (Thread thread : listOfThreads) {
            try {
                thread.join();
                System.out.println(thread.getName() + " was joined.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Collections.nCopies(5, new Object());
    }
}
