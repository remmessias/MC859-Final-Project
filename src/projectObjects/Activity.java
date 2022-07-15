package projectObjects;

import java.util.Arrays;

public class Activity {

    public Integer activityId;
    public int mode;
    public int numberSuccessors;
    public Integer[] successors;
    public Integer[] predecessors;
    public int duration;
    public Integer[] consumptions;
    public Integer start;
    public Integer finish;
    public Integer earliestStart;
    public Integer earliestFinish;
    public Integer latestStart;
    public Integer latestFinish;
    public boolean scheduled;
    public boolean eligible;
    private Integer activityListPosition;

    public Activity(Integer activityId, int mode, Integer[] successors, Integer[] predecessors,
            int duration, Integer[] consumptions, int numberSuccessors) {
        this.activityId = activityId;
        this.mode = mode;
        this.successors = successors;
        this.predecessors = predecessors;
        this.duration = duration;
        this.consumptions = consumptions;
        this.numberSuccessors = numberSuccessors;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", activityDuration=" + duration +
                ", successors=" + Arrays.toString(successors) +
                ", predecessors=" + predecessors +
                ", consumptions=" + Arrays.toString(consumptions) +
                ", earliestStart=" + earliestStart +
                ", earliestFinish=" + earliestFinish +
                ", latestStart=" + latestStart +
                ", latestFinish=" + latestFinish +
                ", scheduled=" + scheduled +
                ", eligible=" + eligible +
                '}';
    }

    public String activityToString() {
        return "Activity:          " + this.activityId + "\n" +
                "Activity list pos: " + this.activityListPosition + "\n" +
                "Duration:          " + this.duration + "\n" +
                "Consumption:       " + Arrays.toString(this.consumptions) + "\n" +
                "Predecessors:      " + Arrays.toString(this.predecessors) + "\n" +
                "Start:             " + this.start + "\n" +
                "Finish:            " + this.finish + "\n" +
                "Earliest start:    " + this.earliestStart + "\n" +
                "Earliest finish:   " + this.earliestFinish + "\n" +
                "Latest start:      " + this.latestStart + "\n" +
                "Latest finish:     " + this.latestFinish + "\n" +
                "Scheduled:         " + this.scheduled + "\n" +
                "Eligible:          " + this.eligible + "\n\n";
    }

    public int getActivityId() {
        return activityId;
    }

    public Integer getActivityListPosition() {
        return activityListPosition;
    }

    public void setActivityListPosition(Integer activityListPosition) {
        this.activityListPosition = activityListPosition;
    }
}
