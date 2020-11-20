package jureczko.page.objectsDTO;

import java.util.List;

public class SlideshowDTO {

    private List<String> imageSrc;
    private String opis;

    public SlideshowDTO(List<String> imageSrc, String opis) {
        this.imageSrc = imageSrc;
        this.opis = opis;
    }

    public List<String> getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(List<String> imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
