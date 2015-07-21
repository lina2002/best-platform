package platform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CoursesController {
    private XMLParser xmlParser = new XMLParser("courses.xml");

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/courses")
    public void courses(Model model) {
        List<Course> courses = xmlParser.parseXML();
        model.addAttribute("courses", courses);
    }

}