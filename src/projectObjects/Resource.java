package projectObjects;

import java.util.Arrays;

public class Resource {

    private int resourceId;
    public Integer[] resourceProfile;
    public int resourceUpperBound;
    public int resourceLowerBound;
    private int planningHorizon;

    public Resource(int resourceId,
            int resourceUpperBound,
            int planningHorizon) {
        this.resourceId = resourceId;
        this.resourceUpperBound = resourceUpperBound;
        this.resourceLowerBound = 0;
        this.planningHorizon = planningHorizon;
        this.resourceProfile = new Integer[planningHorizon];
        Arrays.fill(this.resourceProfile, 0);
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", resourceProfile=" + resourceProfile.length +
                ", resourceUpperBound=" + resourceUpperBound +
                ", resourceLowerBound=" + resourceLowerBound +
                ", planningHorizon=" + planningHorizon +
                '}';
    }

    public String toStringResourceProfile(Integer toPeriod) {
        StringBuilder resultString = new StringBuilder();
        resultString.append("[");
        for (int i = 0; i <= toPeriod; i++) {
            if (this.resourceProfile[i].toString().length() == 1) {
                resultString.append("  " + this.resourceProfile[i].toString() + " ");
            }
            if (this.resourceProfile[i].toString().length() == 2) {
                resultString.append(" " + this.resourceProfile[i].toString() + " ");
            }
            if (this.resourceProfile[i].toString().length() == 3) {
                resultString.append("" + this.resourceProfile[i].toString() + " ");
            }

        }
        resultString.append("]");
        return resultString.toString();
    }

    public int getResourceId() {
        return resourceId;
    }
}