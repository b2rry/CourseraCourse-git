public class Main {
    public static void main(String[] args) {
        WordsInFiles d = new WordsInFiles();
        d.buildWordFileMap();
        d.maxNumber();
        d.wordsInNumFiles(4);
        d.printFilesIn("red");
    }
}