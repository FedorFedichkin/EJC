package task_11_filesparser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * This class was made in order to write content to report.csv file.
 */
public class FileWriterToDisk {

    /**
     * This method creates an empty folder if needed and then creates an empty report.csv file
     * in this folder and writes the content to this file.
     *
     * @param contentToWriteToReport - the concurrentSkipListMap that holds user names as keys (sorted in
     *                               alphabetic order) and Content objects as values. The latter has the only
     *                               field urlAndCorrespondingTime that is also a concurrentSkipListMap with
     *                               url as keys (also sorted in alphabetic order) and time as values.
     */
    void writeContentToReportFile(Map<String, Content> contentToWriteToReport) {
        String filePath = "EJC//src//main//java//task_11_filesparser//Report//";
        String reportFileName = filePath + "report.csv";
        File folderWithReport = new File(filePath);
        File reportFile = new File(reportFileName);
        if (!folderWithReport.exists()) {
            folderWithReport.mkdir();
        }
        if (!reportFile.exists()) {
            try {
                reportFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter writer = new FileWriter(reportFileName)) {
            for (Map.Entry<String, Content> entry : contentToWriteToReport.entrySet()) {
                String currentUserName = entry.getKey();
                Map<String, Long> urlAndTimeForCurrentUser = entry.getValue().getUrlAndCorrespondingTime();
                for (Map.Entry<String, Long> entryForUrlAndTime : urlAndTimeForCurrentUser.entrySet()) {
                    String currentLine = currentUserName + ";" + entryForUrlAndTime.getKey()
                            + ";" + entryForUrlAndTime.getValue() + "\r\n";
                    writer.write(currentLine);
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
