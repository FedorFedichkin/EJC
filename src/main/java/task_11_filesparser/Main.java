package task_11_filesparser;

public class Main {
    public static void main(String[] args) {
        CsvGenerator generator = new CsvGenerator();
        generator.generateCsvFiles();
        new Processor().startParsing();
    }
}
