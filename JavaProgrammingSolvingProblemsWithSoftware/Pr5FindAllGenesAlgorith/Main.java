public class Main {
    public static void main(String[] args) {//!предполагается, если был найден открывающий ген, то новый открывающий найден уже не будет пока не будет найден закрывающий
        //вопрос! если ген открыт, он должен обязательно быть закрыт или если находится новый открывающий тег, то переходить на поиск закрывающего от нового?
        GenesFinderClass finder = new GenesFinderClass();
        //finder.outputAllGenesFromFileWithDna();
        //finder.outputAllGenesFromDna("ATGTGAATGCCCTAACCCCCCCCTAACCCCCCATGCCCTAGCCCCCCATGCCCCCCCCCTGAATGCCCCCCATGC");
        //finder.outputAllGenesFromDna("ATGTGAATGCCCTAACCCCCCCCTAACCCCCCATGCCCTAGCCCCCCATGCCCCCCCCCTGAATGCCCCCCATGC");
        //finder.test(dna);
        FinderClassFromLesson f = new FinderClassFromLesson();
        f.printAllGenes("ATGTAA");
    }
}