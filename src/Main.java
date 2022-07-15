import rcpsp.geneticAlgorithm.*;
import java.io.File;
import java.io.PrintStream;

public class Main {

    public static void main(String args[]) throws Exception {
        String[] pathnames1;
        PrintStream console = System.out;

        File f1 = new File(
                "C:/Users/rebec/Downloads/rcpsp_genetic_algorithm-master/rcpsp_genetic_algorithm-master/data");

        pathnames1 = f1.list();

        for (String pathname1 : pathnames1) {
            String caminho = "C:/Users/rebec/Downloads/rcpsp_genetic_algorithm-master/rcpsp_genetic_algorithm-master/data/"
                    + pathname1;
            String[] pathnames2;
            File f2 = new File(caminho);
            pathnames2 = f2.list();

            for (String pathname2 : pathnames2) {
                File myObj = new File(
                        "C:/Users/rebec/Downloads/rcpsp_genetic_algorithm-master/rcpsp_genetic_algorithm-master/out/"
                                + pathname1 + "/" + pathname2 + ".txt");
                if (myObj.createNewFile()) {
                    System.setOut(console);
                    System.out.println("File created: " + myObj.getName());
                    PrintStream o = new PrintStream(myObj);

                    System.setOut(o);

                    String file = caminho + "/" + pathname2;

                    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(file);
                    for (int i = 0; i < 50; i++) {
                        geneticAlgorithm.solveOnePoint(50, 1000, 0.01, 100);
                    }
                } else {
                    System.out.println("File already exists.");
                }
            }
        }
    }
}
