package artem;

public class Response {
    private String result;
    private String description;

    private Response(String result, String description) {
        this.result = result;
        this.description = description;
    }

    public static Response create(String result, String description) {
        return new Response(result, description);
    }

}
