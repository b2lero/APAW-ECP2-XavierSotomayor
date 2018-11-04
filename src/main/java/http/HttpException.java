package http;

public class HttpException extends RuntimeException {
    private static final long serialVersionUID = 4799414127780941235L;

    private final HttpStatus httpStatus;

    public HttpException(HttpStatus httpStatus, String msg) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
