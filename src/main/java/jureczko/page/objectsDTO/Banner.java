package jureczko.page.objectsDTO;

public class Banner {

    private String name;
    private String path;
    private String quote;

    public Banner() {
    }

    public Banner(String name, String path, String quote) {
        this();
        this.name = name;
        this.path = path;
        this.quote = quote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
