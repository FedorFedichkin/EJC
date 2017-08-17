package task_11_filesparser;

/**
 * This is a class with the main method of an application Filesparser
 * that is designed to simplify the structure of reports on visits of
 * websites by employees during the working hours. This class launches
 * *.csv files generator and Processor that parses them and writes
 * the output to final report.csv file.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Generating *.csv files...");
        CsvGenerator generator = new CsvGenerator();
        generator.generateCsvFiles();
        System.out.println("All *.csv files are generated!");
        System.out.println();
        System.out.println("Parsing files...");
        new Processor().startProcessing();
        System.out.println("Done!");
    }
}
