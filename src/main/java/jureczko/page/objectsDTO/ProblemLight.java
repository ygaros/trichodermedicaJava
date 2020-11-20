package jureczko.page.objectsDTO;

public class ProblemLight {

    private String urlPath;
    private String path;
    private String name;

    public ProblemLight(String urlPath, String path, String name) {
        this.urlPath = urlPath;
        this.path = path;
        this.name = name;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
