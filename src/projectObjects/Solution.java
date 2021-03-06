package projectObjects;

import java.util.Arrays;
import java.util.List;

public class Solution {

    private int solutionId;
    private List listOfActivities;
    private int[] activityList;
    private int[] randomKey;
    private int upperBound;
    private int lowerBound;
    private int fitness;

    public Solution(int solutionId, List listOfActivities, int[] activityList, int[] randomKey, int upperBound,
            int lowerBound, int fitness) {
        this.solutionId = solutionId;
        this.listOfActivities = listOfActivities;
        this.activityList = activityList;
        this.randomKey = randomKey;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "solutionId=" + solutionId +
                ", listOfActivities=" + listOfActivities +
                ", activityList=" + Arrays.toString(activityList) +
                ", randomKey=" + Arrays.toString(randomKey) +
                ", upperBound=" + upperBound +
                ", lowerBound=" + lowerBound +
                ", fitness=" + fitness +
                '}';
    }

    public int getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    public List getListOfActivities() {
        return listOfActivities;
    }

    public void setListOfActivities(List listOfActivities) {
        this.listOfActivities = listOfActivities;
    }

    public int[] getActivityList() {
        return activityList;
    }

    public void setActivityList(int[] activityList) {
        this.activityList = activityList;
    }

    public int[] getRandomKey() {
        return randomKey;
    }

    public void setRandomKey(int[] randomKey) {
        this.randomKey = randomKey;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

}
