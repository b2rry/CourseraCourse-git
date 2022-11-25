import edu.duke.*;

public class MarkovRunnerWithInterface {

	private int seed;
	public MarkovRunnerWithInterface(int seed){
		this.seed = seed;
	}
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
		markov.setRandom(seed);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
	public void runModel(IMarkovModel markov, String text, int size ,boolean isEfficientModel) {
		markov.setTraining(text);
		((EfficientMarkovModel) markov).buildMap();//из-за необъявленного метода для частного случая в интерфейсе, вызов buildMap() только после инициализации myText через setTraining()
		System.out.println("running with " + markov);
		markov.setRandom(seed);
		for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
	}
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		
        //MarkovZero mZ = new MarkovZero();
        //runModel(mZ, st, size);
    
        //MarkovOne mOne = new MarkovOne();
        //runModel(mOne, st, size);
        
        //MarkovModel mMod = new MarkovModel(6);
        //runModel(mMod, st, size);

		EfficientMarkovModel emMod = new EfficientMarkovModel(5);
		runModel(emMod, st, size,true);

    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
}
