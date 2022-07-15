package rcpsp.geneticAlgorithm;

import projectObjects.Project;

public class Chromosome extends Project {
    public Chromosome(int projectId) {
        super(projectId);
    }

    @Override
    public String toString() {
        String resultString = "Chromosome Id:     " + this.projectId + "\n" +
                "Completion:        " + this.completion + "\n";
        return resultString;
    }

    public Integer getCompletion() {
        return this.completion;
    }

    public Integer getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
