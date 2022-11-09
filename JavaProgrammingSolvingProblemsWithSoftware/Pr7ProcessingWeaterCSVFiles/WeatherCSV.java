import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class WeatherCSV {
	public void averageTemperatureInFile(CSVParser parser){
		double averTemp = 0;
		double sumTemp = 0;
		double currTemp = 0;
		int sumCounter = 0;
		for(CSVRecord currentRow : parser) {
			if (currentRow.get("TemperatureF") != "-9999") {
				currTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				sumTemp += currTemp;
				sumCounter++;
			}
		}
		averTemp = sumTemp/sumCounter;
		System.out.println("Average temperature in this file: " + averTemp);
	}

	public void averageTemperatureWithHighHumidityInFile(CSVParser parser, int threshold){
		double averTemp = 0;
		double sumTemp = 0;
		double currTemp = 0;
		int sumCounter = 0;
		int humidity = 0;
		for(CSVRecord currentRow : parser) {
			if(!currentRow.get("Humidity").equals("N/A")) {
				humidity = Integer.parseInt(currentRow.get("Humidity"));
				if (!currentRow.get("TemperatureF").equals("-9999") && humidity >= threshold) {
					currTemp = Double.parseDouble(currentRow.get("TemperatureF"));
					sumTemp += currTemp;
					sumCounter++;
				}
			}
		}
		if (sumCounter == 0){
			System.out.println("In this file there are not such temperatures with humidity more then or equal " + threshold);
		}else{
			averTemp = sumTemp/sumCounter;
			System.out.println("Average temperature with humidity more then or equal " + threshold + " in this file: " + averTemp);
		}

	}
	public CSVRecord hottestHourInFile(CSVParser parser) {
		CSVRecord largestSoFar = null;
		for (CSVRecord currentRow : parser) {
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
		}
		return largestSoFar;
	}

	public CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord lowerSoFar = null;
		for (CSVRecord currentRow : parser) {
			lowerSoFar = getLowerOfTwo(currentRow, lowerSoFar);
		}
		return lowerSoFar;
	}

	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowerSoFar = null;
		for (CSVRecord currentRow : parser) {
			lowerSoFar = getLowerHumidityOfTwo(currentRow, lowerSoFar);
		}
		return lowerSoFar;
	}

	public CSVRecord hottestInManyDays() {
		CSVRecord largestSoFar = null;//вспомогательная ссылка
		DirectoryResource dr = new DirectoryResource();//объект нужен для выбора нескольких файлов
		for (File f : dr.selectedFiles()) {//перебор каждого файла из выбранных, в цикле
			FileResource fr = new FileResource(f);//объект класса, работающего с разными типами данных(в данном случае csv parser)
			CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
		}
		return largestSoFar;
	}

	public File fileWithColdestTemperature(){
		CSVRecord lowerSoFar = null;
		File lowerTempFile = null;
		DirectoryResource dr = new DirectoryResource();
		for (File currFile : dr.selectedFiles()) {//перебор каждого файла из выбранных, в цикле
			FileResource fr = new FileResource(currFile);//объект класса, работающего с разными типами данных(в данном случае csv parser)
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			lowerSoFar = getLowerOfTwo(currentRow, lowerSoFar);
			if(needToRewrite(currentRow, lowerSoFar)) lowerTempFile = currFile;
		}
	return lowerTempFile;
	}

	public File fileWithLowestHumidity(){
		CSVRecord lowerSoFar = null;
		File lowerTempFile = null;
		DirectoryResource dr = new DirectoryResource();
		for (File currFile : dr.selectedFiles()) {//перебор каждого файла из выбранных, в цикле
			FileResource fr = new FileResource(currFile);//объект класса, работающего с разными типами данных(в данном случае csv parser)
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			lowerSoFar = getLowerHumidityOfTwo(currentRow, lowerSoFar);
			if(needToRewrite(currentRow, lowerSoFar)) lowerTempFile = currFile;
		}
		return lowerTempFile;
	}

	public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
		if (largestSoFar == null) {
			largestSoFar = currentRow;
		} else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
			if (currentTemp > largestTemp) {
				largestSoFar = currentRow;
			}
		}
		return largestSoFar;
	}

	public CSVRecord getLowerOfTwo (CSVRecord currentRow, CSVRecord lowerSoFar) {
		if (lowerSoFar == null) {
			lowerSoFar = currentRow;
		} else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double lowerTemp = Double.parseDouble(lowerSoFar.get("TemperatureF"));
			if (currentTemp < lowerTemp && currentTemp != -9999 && lowerTemp != -9999) {
				lowerSoFar = currentRow;
			}
		}
		return lowerSoFar;
	}

	public CSVRecord getLowerHumidityOfTwo (CSVRecord currentRow, CSVRecord lowerSoFar) {
		if (lowerSoFar == null) {
			lowerSoFar = currentRow;
		} else {
			if (!currentRow.get("Humidity").equals("N/A")  && !lowerSoFar.get("Humidity").equals("N/A")) {
				double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
				double lowerTemp = Double.parseDouble(lowerSoFar.get("Humidity"));
				if (currentTemp < lowerTemp) {
					lowerSoFar = currentRow;
				}
			}
		}
		return lowerSoFar;
	}

	public boolean needToRewrite (CSVRecord currentRow, CSVRecord lowerSoFar) {
		if (currentRow.equals(lowerSoFar)) {
			return true;
		}
		return false;
	}
//********************************************************************************************
	public void testHottestHourInFile () {
		FileResource fr = new FileResource();
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
	}

	public void testColdestHourInFile(){
		FileResource fr = new FileResource();
		CSVRecord lower = coldestHourInFile(fr.getCSVParser());
		System.out.println("coldest temperature was " + lower.get("TemperatureF") + " at " + lower.get("DateUTC"));
	}

	public void testHottestInManyDays () {
		CSVRecord largest = hottestInManyDays();
		System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
	}

	public void testFileWithColdestTemperature(){
		File file = fileWithColdestTemperature();
		String name = file.getName();
		System.out.println("Coldest temperature was in file: " + name);
		FileResource fr = new FileResource(file);
		CSVRecord lower = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest temperature at this day was " + lower.get("TemperatureF"));
		System.out.println("All the temperatures at this day were:");
		for(CSVRecord row : fr.getCSVParser()){
			System.out.println(row.get("DateUTC") + ": " + row.get("TemperatureF"));
		}
	}

	public void testFileWithLowestHumidity(){
		File file = fileWithLowestHumidity();
		String name = file.getName();
		System.out.println("Lowest Humidity was in file: " + name);
		FileResource fr = new FileResource(file);
		CSVRecord lower = lowestHumidityInFile(fr.getCSVParser());
		System.out.println("Lowest Humidity at this day was " + lower.get("Humidity"));
		System.out.println("All the Humidites at this day were:");
		for(CSVRecord row : fr.getCSVParser()){
			System.out.println(row.get("DateUTC") + ": " + row.get("Humidity"));
		}
	}

	public void testAverageTemperatureInFile(){
		FileResource fr = new FileResource();
		averageTemperatureInFile(fr.getCSVParser());
	}

	public void testAverageTemperatureWithHighHumidityInFile(){
		FileResource fr = new FileResource();
		averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
	}
}
