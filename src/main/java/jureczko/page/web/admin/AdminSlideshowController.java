package jureczko.page.web.admin;

import jureczko.page.objectsDTO.SlideshowAdminDTO;
import jureczko.page.response.SlideshowPostHandler;
import jureczko.page.service.admin.AdminSlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/slideshow")
public class AdminSlideshowController {

    private final AdminSlideshowService adminSlideshowService;

    @Autowired
    public AdminSlideshowController(AdminSlideshowService adminSlideshowService) {
        this.adminSlideshowService = adminSlideshowService;
    }

    @GetMapping
    public SlideshowAdminDTO getData(){
        return adminSlideshowService.getAllData();
    }

    @PostMapping
    public ResponseEntity<Void> createNew(@RequestBody SlideshowPostHandler slideshow){
        return adminSlideshowService.createNew(slideshow);
    }
}
