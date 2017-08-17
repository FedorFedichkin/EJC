package task_11_filesparser;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 */
public class CsvParser implements Runnable {
    private Map<String, Content> userNamesAndCorrespondingContent;
    private File file;

    public CsvParser(Map<String, Content> userNamesAndCorrespondingContent, File file) {
        this.userNamesAndCorrespondingContent = userNamesAndCorrespondingContent;
        this.file = file;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
            String lineFromFile = reader.readLine();
            String[] splitLine;
            String userName;
            String url;
            long time;
            while ((lineFromFile = reader.readLine()) != null) {
                splitLine = lineFromFile.split(";");
                userName = splitLine[3];
                url = splitLine[1];
                time = Long.parseLong(splitLine[2]);
                if (userNamesAndCorrespondingContent.containsKey(userName)) {
                    Content content = userNamesAndCorrespondingContent.get(userName);
                    if (content.getUrlAndCorrespondingTime().containsKey(url)) {
                        content.updateTimeForCorrespondingUrl(url, time);
                    } else {
                        content.updateUrlAndTimeInfoForCorrespondingUsername(url, time);
                    }
                } else {
                    Map<String, Long> newUrlAndTime = new ConcurrentSkipListMap<>();
                    newUrlAndTime.put(url, time);
                    userNamesAndCorrespondingContent.put(userName, new Content(newUrlAndTime));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
