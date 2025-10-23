package Model;

public class TakenByMatcher implements ITaskMatcher{
    private String takenBy;

    public TakenByMatcher(String takenBy) {
        this.takenBy = takenBy;
    }

    @Override
    public boolean match(Task task) {
        return task.getTakenBy().equals(takenBy);
    }
}
