public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }
    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int ind = 0; ind < myWords.length; ind++) {
            ret += myWords[ind] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()) return false;
        for(int ind = 0; ind < this.length(); ind++){
            if(!myWords[ind].equals(other.myWords[ind])) return false;//.equals(other.wordAt(ind))
        }
        return true;
    }
    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        for(int ind = 0; ind < myWords.length-1; ind++){
            out.myWords[ind] = out.myWords[ind+1];
        }
        out.myWords[myWords.length-1] = word;
        return out;
    }
    public int hashCode(){
        return this.toString().hashCode();
    }
}