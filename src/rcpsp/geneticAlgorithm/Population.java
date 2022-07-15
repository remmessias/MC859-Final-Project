package rcpsp.geneticAlgorithm;

import projectObjects.Activity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Population {

    private Integer populationId;
    private Integer populationSize;
    public Integer averageFitness;
    public Integer bestFitness;
    public Integer worstFitness;
    public Integer generation;
    public ArrayList<Chromosome> listOfChromosomes;
    public ArrayList<ArrayList> listOfTubuActivityList;
    public Integer tabuOverflowCount;
    public Integer scheduleCount;
    public String filePathPspLip;

    public Population(Integer populationId, Integer populationSize, String filePathPspLib) throws Exception {
        this.populationId = populationId;
        this.populationSize = populationSize;
        this.tabuOverflowCount = 0;
        this.scheduleCount = 0;
        this.listOfTubuActivityList = new ArrayList<>();
        this.filePathPspLip = filePathPspLib;

        ArrayList<Chromosome> tmpListOfChromosomes = new ArrayList<>();

        for (int i = 0; i < this.populationSize; i++) {
            Chromosome chromosome = new Chromosome(i + 1);
            chromosome.readPspLibInstance(filePathPspLib);
            tmpListOfChromosomes.add(chromosome);
        }

        this.listOfChromosomes = tmpListOfChromosomes;
    }

    @Override
    public String toString() {
        String result = "Generation:      " + this.generation + "\n" +
                "Population size: " + this.populationSize + "\n" +
                "Best fitness:    " + this.bestFitness + "\n" +
                "Worst fitness:   " + this.worstFitness + "\n" +
                "Average fitness: " + this.averageFitness + "\n\n";
        return result;
    }

    public void serialScheduleGenerationSchemeActivityList() {
        for (int i = 0; i < this.listOfChromosomes.size(); i++) {
            this.listOfChromosomes.get(i).serialScheduleGeneratorSchemeActivityList();
            this.scheduleCount += 1;
        }
    }

    public void evaluateFitness() {
        this.sortByFitness();
        this.bestFitness = this.listOfChromosomes.get(0).completion;
        this.worstFitness = this.listOfChromosomes.get(this.listOfChromosomes.size() - 1).completion;
        Integer sumCompletion = 0;
        for (Chromosome chromosome : this.listOfChromosomes) {
            sumCompletion += chromosome.completion;
        }
        this.averageFitness = sumCompletion / this.listOfChromosomes.size();
    }

    public void onePointCrossOver(int tabuRuns) {
        ArrayList parent1 = this.listOfChromosomes.get(this.getRandomNumberInRange(0, 1)).activityList;
        ArrayList parent2 = this.listOfChromosomes.get(this.getRandomNumberInRange(2, 4)).activityList;

        ArrayList<Integer> child1 = new ArrayList<>();
        ArrayList<Integer> child2 = new ArrayList<>();

        loop: {
            for (int i = 0; i < tabuRuns; i++) {
                Integer point = new Random().nextInt(parent1.size());
                child1 = createChild(parent1, parent2, point);
                child2 = createChild(parent2, parent1, point);
                if (!this.listOfTubuActivityList.contains(child1)) {
                    break loop;
                }
                if (i == tabuRuns - 1) {
                    this.tabuOverflowCount += 1;
                }
            }
        }

        this.listOfTubuActivityList.add(child1);
        this.listOfTubuActivityList.add(child2);

        this.updateChromosomeByActivityList(this.listOfChromosomes.size() - 1, child1);
        this.listOfChromosomes.get(this.listOfChromosomes.size() - 1).setProjectId(
                this.listOfChromosomes.stream().max(Comparator.comparing(Chromosome::getProjectId)).get().projectId
                        + 1);
        this.updateChromosomeByActivityList(this.listOfChromosomes.size() - 2, child2);
        this.listOfChromosomes.get(this.listOfChromosomes.size() - 2).setProjectId(
                this.listOfChromosomes.stream().max(Comparator.comparing(Chromosome::getProjectId)).get().projectId
                        + 1);
    }

    public void mutationRandomActivityList(double epsilon, Integer tabuRuns) {
        this.sortByFitness();
        for (int i = Math.round(this.listOfChromosomes.size() / 2); i < (this.listOfChromosomes.size() - 3); i++) {
            if (new Random().nextDouble() > epsilon) {
                this.listOfChromosomes.get(i).setProjectId(this.listOfChromosomes.stream()
                        .max(Comparator.comparing(Chromosome::getProjectId)).get().projectId + 1);

                this.listOfTubuActivityList.add(this.listOfChromosomes.get(i).activityList);

                innerLoop: {
                    for (int j = 0; j < tabuRuns; j++) {
                        this.listOfChromosomes.get(i).randomActivityList();
                        if (!listOfTubuActivityList.contains(this.listOfChromosomes.get(i).activityList)) {
                            break innerLoop;
                        }
                        if (j == tabuRuns - 1) {
                            this.tabuOverflowCount += 1;
                        }
                    }
                }
                this.listOfChromosomes.get(i).setActivityListPosByActivityList();
            }
        }
    }

    public void sortByFitness() {
        this.listOfChromosomes.sort(Comparator.comparing(Chromosome::getCompletion));
    }

    public void printBestSchedule() {
        this.sortByFitness();
        System.out.println(this.listOfChromosomes.get(0).projectToString(0, 90));
    }

    public void printScheduleCount() {
        System.out.println("Schedule count:         " + this.scheduleCount);
        System.out.println("PSPLIP instance:        " + this.filePathPspLip);
    }

    public void printTabuOverflowCount() {
        System.out.println("Tabu overflow count:    " + this.tabuOverflowCount);
    }

    private ArrayList createChild(ArrayList<Integer> parent1, ArrayList<Integer> parent2, Integer crossOverPoint) {
        ArrayList<Integer> child = new ArrayList(parent1.subList(0, crossOverPoint));
        for (Integer integer : parent2) {
            if (!child.contains(integer)) {
                child.add(integer);
            }
        }

        return child;
    }

    private void updateChromosomeByActivityList(Integer chromosomeIndex, ArrayList<Integer> activityList) {
        this.listOfChromosomes.get(chromosomeIndex).activityList = activityList;
        for (Activity activity : this.listOfChromosomes.get(chromosomeIndex).listOfActivities) {
            activity.setActivityListPosition(activityList.indexOf(activity.getActivityId()));
        }
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
