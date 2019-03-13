package artem;

public class NotTimeException extends RuntimeException {

    public NotTimeException(String timeExpression) {
        super(timeExpression);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " time format does't acceptable";
    }
}
