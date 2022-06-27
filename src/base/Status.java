package base;

public enum Status {

    NEW("новая", "new"),
    IN_PROGRESS("в работе", "work"),
    READY("готово", "ready");


    private String taskStatus;
    private String keyword;

    Status(String taskStatus, String keyword) {
        this.taskStatus = taskStatus;
        this.keyword = keyword;
    }


    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public String getKeyword() {
        return keyword;
    }
}