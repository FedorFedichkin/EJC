package task_11_filesparser;

import java.util.Map;

/**
 * This class is designed mainly for storage of pieces of information (websites url and spent time)
 * read from *.csv files. The second purpose of this class is updating this information.
 */
public class Content {
    private Map<String, Long> urlAndCorrespondingTime;

    Content(Map<String, Long> urlAndCorrespondingTime) {
        this.urlAndCorrespondingTime = urlAndCorrespondingTime;
    }

    Map<String, Long> getUrlAndCorrespondingTime() {
        return urlAndCorrespondingTime;
    }

    void updateTimeForCorrespondingUrl(String url, long newTimeValue) {
        long oldTimeValue = this.urlAndCorrespondingTime.get(url);
        this.urlAndCorrespondingTime.put(url, oldTimeValue + newTimeValue);
    }

    void updateUrlAndTimeInfoForCorrespondingUsername(String url, long time) {
        this.urlAndCorrespondingTime.put(url, time);
    }
}
