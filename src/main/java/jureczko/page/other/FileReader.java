package jureczko.page.other;

import jureczko.page.objects.Image;
import jureczko.page.objects.Problem;
import jureczko.page.objects.Usluga;
import jureczko.page.objects.Zabieg;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileReader {

    private static String TXT_DIR;
    private static String IMAGE_DIR;

    static {
        try {
            String path = new File(".").getCanonicalPath();
            TXT_DIR = path + "/storage/teksty";
            IMAGE_DIR = path +"/storage/zdjecia";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] readTXTFile(File file){
        List<String> res = new ArrayList<>();
        try(InputStream fis = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader bReader = new BufferedReader(reader)){
            bReader.lines().forEach(res::add);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toArray(new String[0]);
    }
    private File[] readAllImages(File directory){
        return directory.listFiles((f, name) -> name.contains(".jpg") ||
                name.contains(".png") ||
                name.contains(".img") ||
                name.contains(".jpeg"));
    }
    private List<Image> getAllImages(File[] images){
        List<Image> root = new ArrayList<>();
        Arrays.stream(images)
                .forEach(i -> root.add(new Image(i.getName().substring(0, i.getName().lastIndexOf(".")),"/files/"+i.getParentFile().getName()+"/"+i.getName())));
        return root;
    }

    private Holder getProblems(List<Image> zdjecia, List<String[]> txt) throws FileNotFoundException {
        Holder holder = new Holder();
        int counter = 0;
        while(!txt.isEmpty()) {
            for (Image image : zdjecia) {
                String[] strings = txt.get(counter);
                String header = strings[0];
                String name = image.getName();
                if (header.equalsIgnoreCase(name)) {
                    strings[0] = "";
                    holder.getProblems().add(new Problem(String.join(" ", strings),
                            image, header, "/problem/"+removeStrangeChars(header)));
                    txt.remove(counter);
                    zdjecia.remove(image);
                    break;
                }else if(header.contains("zabieg")){
                    header = header.replace("zabieg", "");
                    String type = this.getTypeFromHeader(header);
                    header = strings[1];
                    if(header.equalsIgnoreCase(image.getName())) {
                        strings[0] = "";
                        strings[1] = "";
                        double priceOnce = Double.parseDouble(strings[2]);
                        double priceSeries = Double.parseDouble(strings[3]);
                        strings[2] = "";
                        strings[3] = "";
                        holder.getZabiegs().add(new Zabieg(header, String.join(" ", strings),
                                "/zabieg/"+type.toLowerCase()+"/"+this.removeStrangeChars(header), 10L, type, image,
                                priceOnce, priceSeries));
                        txt.remove(counter);
                        zdjecia.remove(image);
                        break;
                    }
                }
            }
        }
        return holder;

    }

    private String getTypeFromHeader(String header) throws FileNotFoundException{
        if(header.contains("KOSMETOLOGICZNY")){
            header = header.replace("KOSMETOLOGICZNY", "");
            return "KOSMETOLOGICZNY";
        }else if(header.contains("TRYCHOLOGICZNY") && !header.contains("TRYCHOLOGICZNY-APARATUROWY")){
            header = header.replace("TRYCHOLOGICZNY", "");
            return "TRYCHOLOGICZNY";
        }else if(header.contains("TRYCHOLOGICZNY-APARATUROWY")){
            header = header.replace("TRYCHOLOGICZNY-APARATUROWY", "");
            return "TRYCHOLOGICZNY-APARATUROWY";
        }

        throw new FileNotFoundException("Bledna kategoria");
    }

    private List<String[]> allText(File dir){
        return Arrays.stream(Objects.requireNonNull(dir
                .listFiles((f, name) -> name.contains(".txt"))))
                .map(this::readTXTFile)
                .collect(Collectors.toList());

    }
    public Holder getAllValues(){
        try {
            return getProblems(
                    getAllImages(
                            readAllImages(new File(IMAGE_DIR))), allText(new File(TXT_DIR)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Image> getZdjecia(){
        return getAllImages(readAllImages(new File(IMAGE_DIR)));
    }

    public static String removeStrangeChars(String str) {
        return str
                .toLowerCase()
                .replaceAll("ł", "l")
                .replaceAll("ó", "o")
                .replaceAll("ą", "a")
                .replaceAll("ę", "e")
                .replaceAll("ż", "z")
                .replaceAll("ź", "z")
                .replaceAll("ń", "n")
                .replaceAll("ć", "c")
                .replaceAll("ś", "s")
                .replaceAll(" ", "-")
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\\p{M}", "");
    }
    public List<Usluga> getUslugs(List<Zabieg> zabiegs){
        List<Usluga> uslugi =  zabiegs.stream()
                .map(z -> new Usluga(z.getName(), z.getCategory(),
                        z.getPriceOnce(), z.getPriceSeries(), z))
                .collect(Collectors.toList());
        uslugi.add(new Usluga("Masaż linfatyczny skóry głowy",
                "TRYCHOLOGICZNY",
                80.0, 0.0, null));
        uslugi.add(new Usluga("Wizyta kontrolna",
                "DIAGNOSTYCZNY",
                60.0, 0.0, null));
        uslugi.add(new Usluga("Badanie skóry głowy trichoskopem + konsultacja trychologiczna",
                "DIAGNOSTYCZNY",
                80.0, 0.0, null));
        return uslugi;
    }
    
}

