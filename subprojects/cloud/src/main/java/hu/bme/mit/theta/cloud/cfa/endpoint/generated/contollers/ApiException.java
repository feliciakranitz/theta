package hu.bme.mit.theta.cloud.cfa.endpoint.generated.contollers;

public class ApiException extends Exception {
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
