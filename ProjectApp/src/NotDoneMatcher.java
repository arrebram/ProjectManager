public class NotDoneMatcher implements ITaskMatcher{
    private NotDoneMatcher(){
    }

    @Override
    public boolean match(Task task) {
        return task.getState != TaskState.DONE;
    }
}
