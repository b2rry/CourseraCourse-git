public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    public PhraseFilter(String where, String phrase){
        this.where = where;
        this.phrase = phrase;
    }
    public  boolean satisfies(QuakeEntry qe) {
        if(where.equals("start")){
            return qe.getInfo().indexOf(phrase) == 0;
        } else if (where.equals("end")) {
            return qe.getInfo().indexOf(phrase) == qe.getInfo().length()-phrase.length();
        } else if (where.equals("any")) {
            return qe.getInfo().indexOf(phrase) != -1;
        }
        System.out.println("incorrect input!");
        return false;
    }
}
