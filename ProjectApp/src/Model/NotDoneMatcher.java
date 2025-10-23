package Model;

import Model.ITaskMatcher;

public class NotDoneMatcher implements ITaskMatcher {

    @Override
    public boolean match(Task task) {
        return task.getState() != TaskState.DONE;
    }
}
