package jureczko.page.response;

import jureczko.page.objectsDTO.Banner;

public class OmnieResponse {
    private Banner banner;
    private String description;

    public OmnieResponse() {
    }

    public OmnieResponse(Banner banner, String description) {
        this();
        this.banner = banner;
        this.description = description;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
