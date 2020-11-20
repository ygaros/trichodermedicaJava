package jureczko.page.objectsDTO;

public class ZabiegDTO {

    private String urlPath;
    private String name;

    public ZabiegDTO(String urlPath, String name) {
        this.urlPath = urlPath;
        this.name = name;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
