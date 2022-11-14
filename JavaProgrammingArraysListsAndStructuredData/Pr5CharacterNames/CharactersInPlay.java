import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> supposeCharacter;
    private ArrayList<Integer> correspondCounts;

    public CharactersInPlay() {
        supposeCharacter = new ArrayList<String>();
        correspondCounts = new ArrayList<Integer>();
    }

    public void update(String name){
        name = name.toUpperCase();
        int ind = supposeCharacter.indexOf(name);
        if(ind != -1){
            int freq = correspondCounts.get(ind);
            correspondCounts.set(ind,freq+1);
        }else{
            supposeCharacter.add(name);
            correspondCounts.add(1);
        }
    }
    public void findAllCharacters(String pathToFile){
        FileResource resource = new FileResource(pathToFile);

        for(String str : resource.lines()){
            str = str.toUpperCase();
            int ind = str.indexOf(".");
            if(ind != -1){
                update(str.substring(0,ind));
            }
        }
    }
    public void charactersWithNumParts(int more, int less){
        for(int indOfCh = 0; indOfCh < supposeCharacter.size(); indOfCh++){
            if(correspondCounts.get(indOfCh) >= more && correspondCounts.get(indOfCh) <= less){
                System.out.println("Character: "+ supposeCharacter.get(indOfCh)+ "\nAmount of phrases: "+correspondCounts.get(indOfCh)+"\n");
            }
        }
    }
    public void tester(){
        String path = "/home/kirill/IdeaProjects/course-3/pr5_CharacterNames/likeit.txt";
        findAllCharacters(path);
        charactersWithNumParts(10,15);
    }
}
