public class Main {
    public static void main(String[] args) {//!предполагается, если был найден открывающий ген, то новый открывающий найден уже не будет пока не будет найден закрывающий
        GenesFinderClass finder = new GenesFinderClass();
        //finder.outputAllGenesFromDna("ATGTGAATGCCCTAACCCCCCCCTAACCCCCCATGCCCTAGCCCCCCATGCCCCCCCCCTGAATGCCCCCCATGC");
        finder.outputAllGenesFromDna("ATGCCCCTAACATGTAA");
    }
}