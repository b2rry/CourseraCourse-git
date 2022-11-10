import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyBirths {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int numOfNames = 0;
		int numOfF = 0;
		int numOfM = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			numOfNames++;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				numOfM++;
			}
			else {
				totalGirls += numBorn;
				numOfF++;
			}
		}
		System.out.println("Total amount of names = " + numOfNames + ". " + "Total births = " + totalBirths);
		System.out.println("Amount of female names = " + numOfF + ". " + "Female births = " + totalGirls);
		System.out.println("Amount of male names = " + numOfM + ". " + "Male births = " + totalBoys);
	}

	public void getRank(String year, String name, String gender){
		String filePath = "/home/kirill/IdeaProjects/pr8_babyNames/src/data/yob" + year + ".csv";
		FileResource fr = new FileResource(filePath);
		int rank = 1;
		boolean isNameFlag = false;
		boolean restRank = false;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if(!restRank && rec.get(1).equals("M")){
				rank = 1;
				restRank = true;
			}
			if(rec.get(0).equals(name)) {
				if(rec.get(1).equals(gender)){
					System.out.println("Rank: "+rank+"\nName: "+name+"\nGender: "+gender+"\nAmount of births: "+rec.get(2));
					isNameFlag = true;
					break;
				}
			}
			rank++;
		}
		if(!isNameFlag){
			System.out.println("Rank: -1\nName: "+name+"\nGender: "+gender+"\nAmount of births: 0");
		}
	}

	public void getName(String year, int rank, String gender){
		String filePath = "/home/kirill/IdeaProjects/pr8_babyNames/src/data/yob" + year + ".csv";
		FileResource fr = new FileResource(filePath);
		String name = null;
		boolean isRankFlag = false;
		boolean restRank = false;
		int countRank = 1;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if(!restRank && rec.get(1).equals("M")){
				countRank = 1;
				restRank = true;
			}
			if(rank == countRank) {
				if(rec.get(1).equals(gender)){
					System.out.println("Rank: "+rank+"\nName: "+rec.get(0)+"\nGender: "+gender+"\nAmount of births: "+rec.get(2));
					isRankFlag = true;
					break;
				}
			}
			countRank++;
		}
		if(!isRankFlag){
			System.out.println("In this file are not position with that rank!");
		}
	}

	public void whatIsNameInYear(String name, String year, String newYear, String gender){
		String birthFilePath = "/home/kirill/IdeaProjects/pr8_babyNames/src/data/yob" + year + ".csv";
		String newFilePath = "/home/kirill/IdeaProjects/pr8_babyNames/src/data/yob" + newYear + ".csv";
		FileResource firstFr = new FileResource(birthFilePath);
		boolean restRank = false;
		int countRank = 1;
		int rank = 0;
		for (CSVRecord rec : firstFr.getCSVParser(false)) {
			if(!restRank && rec.get(1).equals("M")){
				countRank = 1;
				restRank = true;
			}
			if(rec.get(0).equals(name)) {
				if(rec.get(1).equals(gender)){
					rank = countRank;
					System.out.println("\nYour info:\nYear: "+year+"\nRank: "+rank+"\nName: "+rec.get(0)+"\nGender: "+gender+"\nAmount of births: "+rec.get(2));
					break;
				}
			}
			countRank++;
		}
		FileResource secondFr = new FileResource(newFilePath);
		restRank = false;
		countRank = 1;
		for (CSVRecord rec : secondFr.getCSVParser(false)) {
			if(!restRank && rec.get(1).equals("M")){
				countRank = 1;
				restRank = true;
			}
			if(countRank == rank) {
				if(rec.get(1).equals(gender)){
					System.out.println("\nYour info for different year:\nYear: "+newYear+"\nRank: "+rank+"\nName: "+rec.get(0)+"\nGender: "+gender+"\nAmount of births: "+rec.get(2));
					break;
				}
			}
			countRank++;
		}
	}

	public void yearOfHighestRank(String name, String gender){
		DirectoryResource dr = new DirectoryResource();//объект нужен для выбора нескольких файлов
		boolean foundFlag = false;
		int highestRank = 0;
		String foundYear = null;
		int resAmountOfBirths = 0;
		for (File f : dr.selectedFiles()) {//перебор каждого файла из выбранных, в цикле
			FileResource fr = new FileResource(f);//объект класса, работающего с разными типами данных(в данном случае csv parser)
			boolean restRank = false;
			int countRank = 1;
			for (CSVRecord rec : fr.getCSVParser(false)) {
				if(!restRank && rec.get(1).equals("M")){
					countRank = 1;
					restRank = true;
				}
				if(rec.get(0).equals(name)) {
					if(rec.get(1).equals(gender)){
						if(highestRank == 0 || highestRank > countRank){
							highestRank = countRank;
							foundYear = f.getName().substring(3,7);
							resAmountOfBirths = Integer.parseInt(rec.get(2));
							foundFlag = true;
						}
						break;
					}
				}
				countRank++;
			}
		}
		if(foundFlag) {
			System.out.println("For your data: Name - " + name + ", gender - " + gender + "\nYear with highest rate of name - " + foundYear + ", rank - " + highestRank + ", amount of births - " + resAmountOfBirths);
		}
		else{
			System.out.println("For your data: Name - " + name + ", gender - " + gender + " did not find needed info");
		}
	}

	public void getAverageRank(String name, String gender){
		DirectoryResource dr = new DirectoryResource();//объект нужен для выбора нескольких файлов
		boolean foundFlag = false;
		int sumOfRanks = 0;
		int counterRanks = 0;
		for (File f : dr.selectedFiles()) {//перебор каждого файла из выбранных, в цикле
			FileResource fr = new FileResource(f);//объект класса, работающего с разными типами данных(в данном случае csv parser)
			boolean restRank = false;
			int countRank = 1;
			for (CSVRecord rec : fr.getCSVParser(false)) {
				if(!restRank && rec.get(1).equals("M")){
					countRank = 1;
					restRank = true;
				}
				if(rec.get(0).equals(name)) {
					if(rec.get(1).equals(gender)){
							sumOfRanks += countRank;
							counterRanks ++;
							foundFlag = true;
						break;
					}
				}
				countRank++;
			}
		}
		if(foundFlag) {
			System.out.println("For your data: Name - " + name + ", gender - " + gender + "\nThe average rank of your name in this files - "+ (double)sumOfRanks/counterRanks);
		}
		else{
			System.out.println("For your data: Name - " + name + ", gender - " + gender + " did not find no one rank in this files");
		}
	}

	public void getTotalBirthsRankedHigher(String year, String name, String gender){//метод вернет сумму рожденных детей с рангами имени выше чем ребенка с введенным именем
		String filePath = "/home/kirill/IdeaProjects/pr8_babyNames/src/data/yob" + year + ".csv";
		FileResource fr = new FileResource(filePath);
		boolean isNameFlag = false;
		boolean restRank = false;
		int countRank = 1;
		int sumOfBirths = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if(!restRank && rec.get(1).equals("M")){
				countRank = 1;
				restRank = true;
				sumOfBirths = 0;
			}
			if(rec.get(0).equals(name)) {
				if(rec.get(1).equals(gender)){
					isNameFlag = true;
					break;
				}
			}
			sumOfBirths += Integer.parseInt(rec.get(2));
			countRank++;
		}
		if(isNameFlag){
			System.out.println("Answer - " + sumOfBirths);
		}else{
			System.out.println("-1");
		}
	}

	//***************************************************************************************

	public void testTotalBirths () {
		FileResource fr = new FileResource("/home/kirill/IdeaProjects/pr8_babyNames/src/data/yob1905.csv");
		totalBirths(fr);
	}
	public void testGetRank(){
		getRank("1971","Frank","M");
	}
	public void testGetName(){
		getName("1982",450,"M");
	}
	public void testWhatIsNameInYear(){
		whatIsNameInYear("Owen", "1974", "2014", "M");
	}
	public void testYearOfHighestRank(){
		yearOfHighestRank("Mich", "M");
	}
	public void testGetAverageRank(){
		getAverageRank("Robert", "M");
	}
	public void testGetTotalBirthsRankedHigher(){
		getTotalBirthsRankedHigher("1990","Drew", "M");
	}
}
