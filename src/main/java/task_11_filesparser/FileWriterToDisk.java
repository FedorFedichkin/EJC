package task_11_filesparser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 *
 */
public class FileWriterToDisk {

    synchronized void writeContentToFile(Map<String, Content> contentToWriteToReport) {
        String filePath = "EJC//src//main//java//task_11_filesparser//Report//";
        String reportFileName = filePath + "report.csv";
        File folderWithReport = new File(filePath);
        File reportFile = new File(reportFileName);
        if (!folderWithReport.exists()) {
            folderWithReport.mkdir();
            if (!reportFile.exists()) {
                try {
                    reportFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (folderWithReport.exists()) {
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
