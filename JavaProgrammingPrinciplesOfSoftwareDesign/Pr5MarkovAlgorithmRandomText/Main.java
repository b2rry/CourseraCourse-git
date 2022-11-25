import edu.duke.FileResource;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MarkovRunnerWithInterface run = new MarkovRunnerWithInterface(615);
        run.runMarkov();
    }
    public void testGetFollows(){
        //MarkovRunner run = new MarkovRunner();
        MarkovOne run = new MarkovOne();
        //ArrayList<String> answer = run.getFollows("is");
        //System.out.println(answer);
    }
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne run = new MarkovOne();
        ArrayList<String> answer = run.getFollows("th");
        System.out.println(answer+"\n"+answer.size());
    }
}