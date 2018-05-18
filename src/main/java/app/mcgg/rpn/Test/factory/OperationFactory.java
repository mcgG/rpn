package app.mcgg.rpn.Test.factory;

public class OperationFactory {

    private String operator;

    public OperationFactory(String operator) {
        this.operator = operator;
    }

    public Operation getOperation() {
        Operation op = null;
        try {
            op = (Operation)Class.forName(this.operator).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return op;
    }
}
