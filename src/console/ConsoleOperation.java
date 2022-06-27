package console;

public enum ConsoleOperation {

    SHOW(1),
    EDIT_STATUS(2),
    EXIT(3);
    private int operationId;

    ConsoleOperation(int operationId) {
        this.operationId = operationId;
    }

    public int getOperationId() {
        return operationId;
    }
}
