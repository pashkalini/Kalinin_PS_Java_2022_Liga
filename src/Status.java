public enum Status {

    NEW("новая"),
    IN_PROGRESS("в работе"),
    READY("готово");

    private final String status;

    Status(String status) {
        this.status = status;
    }
    public  String getStatus() {
        return status;
    }

}
