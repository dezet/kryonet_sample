public class SimpleRequest {

    private String text;

    SimpleRequest() {
    }

    public SimpleRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
