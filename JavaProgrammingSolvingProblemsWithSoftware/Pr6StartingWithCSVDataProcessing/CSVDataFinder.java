import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVDataFinder {
    public CSVParser tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        return parser;
    }
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)){
                return record.get("Country")+": "+record.get("Exports")+": "+record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int counter = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)){
                counter++;
            }
        }
        return counter;
    }
    public void bigExporters(CSVParser parser, String value){
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > value.length()){
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
        }
    }
}
