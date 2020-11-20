package jureczko.page.other;

import jureczko.page.data.*;
import jureczko.page.objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DBLoader implements CommandLineRunner {


    private final ProblemRepository problemRepository;
    private final ImageRepository imageRepository;
    private final SlideshowRepository slideshowRepository;
    private final NavBarHrefRepository navBarHrefRepository;
    private final KontaktRepository kontaktRepository;
    private final OpenHourRepository openHourRepository;
    private final AuthorityRepository authRepository;
    private final UserRepository userRepository;
    private final ZabiegRepository zabiegRepository;
    private final UslugaRepository uslugaRepository;

    //nav
    @Value("${navbar}")
    private String[] hrefy;

    //kontakt
    @Value("${kontakt.email}")
    private String email;
    @Value("${kontakt.telefon}")
    private String phone;
    @Value("${kontakt.zipCode}")
    private String zipCode;
    @Value("${kontakt.street}")
    private String street;
    @Value("${kontakt.houseNumber}")
    private String houseNumber;
    @Value("${kontakt.city}")
    private String city;

    //godziny otwarcia
    @Value("${godziny.otwarcia.first}")
    private String[] defaultFirstPair;
    @Value("${godziny.otwarcia.second}")
    private String[] defaultSecondPair;

    @Autowired
    public DBLoader(ProblemRepository problemRepository,
                    SlideshowRepository slideshowRepository,
                    NavBarHrefRepository navBarHrefRepository,
                    KontaktRepository kontaktRepository,
                    OpenHourRepository openHourRepository,
                    AuthorityRepository authRepository,
                    UserRepository userRepository,
                    ImageRepository imageRepository,
                    ZabiegRepository zabiegRepository,
                    UslugaRepository uslugaRepository) {
        this.problemRepository = problemRepository;
        this.slideshowRepository = slideshowRepository;
        this.navBarHrefRepository = navBarHrefRepository;
        this.kontaktRepository = kontaktRepository;
        this.openHourRepository = openHourRepository;
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.zabiegRepository = zabiegRepository;
        this.uslugaRepository = uslugaRepository;
    }

    @Override
    public void run(String... args) {
        FileReader fileReader = new FileReader();
        Holder holder = fileReader.getAllValues();
        List<Problem> lista  = holder.getProblems();
        List<Zabieg> zabiegs = holder.getZabiegs();
        List<Image> zdjecia = fileReader.getZdjecia();
        zdjecia = imageRepository.saveAll(zdjecia);
        for (Problem problem : lista) {
            problem.setImage(zdjecia.stream()
            .filter(i -> i.getName().equalsIgnoreCase(problem.getName()))
            .findFirst().orElseThrow(()-> new IllegalArgumentException("nie ma")));
            String first = String.valueOf(problem.getName().charAt(0));
            problem.setName(problem.getName().replaceFirst(first, first.toUpperCase()));
        }
        for (Zabieg zabieg : zabiegs) {
            zabieg.setImage(zdjecia.stream()
                    .filter(i -> i.getName().equalsIgnoreCase(zabieg.getName()))
                    .findFirst().orElseThrow(()-> new IllegalArgumentException("nie ma")));
            String first = String.valueOf(zabieg.getName().charAt(0));
            zabieg.setName(zabieg.getName().replaceFirst(first, first.toUpperCase()));
        }
        problemRepository.saveAll(lista);
        zabiegRepository.saveAll(zabiegs);
        uslugaRepository.saveAll(fileReader.getUslugs(zabiegs));
        Slideshow sl = new Slideshow("<p><strong>W gabinecie TRICHODERMEDICA trycholog</strong>:</p>" +
                "<ul><li>przeprowadzi wywiad dotyczący stanu zdrowia</li>"+
                "<li>wykona badanie skóry głowy za pomocą <a class=\"text-decoration-none\" href=\"/trichoskopia\">trichoskopu</a></li>"+
                "<li>zdiagnozuje przyczynę danego problemu skóry głowy</li>"+
                "<li>zaleci oraz wykona specjalistyczne zabiegi trychologiczne wspomagające leczenie danego schorzenia</li>"+
                "<li>dobierze odpowiednie preparaty trychologiczne</li>"+
                "<li>w przypadku potrzeby, skieruje na badania analityczne</li>"+
                "<li>wykonane przez lekarza specjalistę (m.in. endokrynologa, dermatologa, immunologa).</li></ul>", "pierwszy pokaz", zdjecia.subList(1, 7));
        slideshowRepository.save(sl);
        navBarHrefRepository.saveAll(Arrays.stream(hrefy)
                .map(s -> new NavBarHref(s, "/"+removeStrangeChars(s.replaceAll(" ", "").toLowerCase())))
                .collect(Collectors.toList()));
        Kontakt kontakt = new Kontakt();
        kontakt.setCity(city);
        kontakt.setZipCode(zipCode);
        kontakt.setEmail(email);
        kontakt.setPhone(phone);
        kontakt.setHouseNumber(houseNumber);
        kontakt.setStreet(street);
        List<OpenHours> listaGodzin = new ArrayList<>();
        String[] days = {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek"};
        for (int i = 0; i < defaultFirstPair.length &&
                i<defaultSecondPair.length; i++) {
            listaGodzin.add(new OpenHours(days[i],
                    defaultFirstPair[i],
                    defaultSecondPair[i]));

        }
        kontakt.setOpenHours(listaGodzin);
        openHourRepository.saveAll(listaGodzin);
        kontaktRepository.save(kontakt);


    }
    private String removeStrangeChars(String str) {
        if(str.equalsIgnoreCase("stronagłówna")){
            return "";
        }
        str = str
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\\p{M}", "")
                .replaceAll("ł", "l");
        return str;
    }
}
