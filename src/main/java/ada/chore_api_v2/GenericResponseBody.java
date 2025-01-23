package ada.chore_api_v2;

public class GenericResponseBody {
    private String message;

    public GenericResponseBody(String message) {
        this.message = message;
    }

    public GenericResponseBody(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
