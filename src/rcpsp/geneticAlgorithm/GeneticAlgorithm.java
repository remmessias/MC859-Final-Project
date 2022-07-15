package rcpsp.geneticAlgorithm;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class GeneticAlgorithm {

    private String filePathPspLib;
    private long executionTime;

    public GeneticAlgorithm(String filePathPspLib) {
        this.filePathPspLib = filePathPspLib;
    }

    public void solveOnePoint(Integer populationSize,
            Integer generationLimit,
            double mutationEpsilon,
            Integer tabuRuns) throws Exception {

        Population population = new Population(1, populationSize, this.filePathPspLib);

        long startTime = System.currentTimeMillis();
        population.serialScheduleGenerationSchemeActivityList();
        population.evaluateFitness();
        population.generation = 0;

        for (int i = 0; i < generationLimit; i++) {

            population.generation += 1;

            population.onePointCrossOver(tabuRuns);

            population.mutationRandomActivityList(mutationEpsilon, tabuRuns);

            population.serialScheduleGenerationSchemeActivityList();
            population.evaluateFitness();

            printProgress(startTime, generationLimit, i + 1, population.bestFitness, population.averageFitness);
        }

        long stopTime = System.currentTimeMillis();
        executionTime = stopTime - startTime;

        System.out.println("\n");
        population.printBestSchedule();
        population.printTabuOverflowCount();
        population.printScheduleCount();

        this.printExecutionTime();
    }

    private void printExecutionTime() {
        System.out.println((this.executionTime / 1000.0));
    }

    private static void printProgress(long startTime, long total, long current, int bestFitness, int averageFitness) {
        long eta = current == 0 ? 0 : (total - current) * (System.currentTimeMillis() - startTime) / current;

        String etaHms = current == 0 ? "N/A"
                : String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(eta),
                        TimeUnit.MILLISECONDS.toMinutes(eta) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(eta) % TimeUnit.MINUTES.toSeconds(1));

        StringBuilder string = new StringBuilder(140);
        int percent = (int) (current * 100 / total);
        string
                .append('\r')
                .append(String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")))
                .append(String.format(" %d%% [", percent))
                .append(String.join("", Collections.nCopies(percent, "=")))
                .append('>')
                .append(String.join("", Collections.nCopies(100 - percent, " ")))
                .append(']')
                .append(String.join("",
                        Collections.nCopies((int) (Math.log10(total)) - (int) (Math.log10(current)), " ")))
                .append(String.format("Generations: %d/%d, ETA: %s", current, total, etaHms))
                .append(", Best fitness: " + bestFitness)
                .append(", Average fitness: " + averageFitness);

        System.out.print(string);
    }
}
