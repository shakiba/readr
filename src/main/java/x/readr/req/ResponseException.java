package x.readr.req;

public class ResponseException extends Exception {
    private static final long serialVersionUID = 1L;

    private final int code;
    private final String message;

    public ResponseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
