package platform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CoursesController {
    private XMLParser xmlParser = new XMLParserImpl("courses.xml");

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

    @RequestMapping("/course")
    public void course(@RequestParam(value = "id", required = false) String idFromGet, Model model) {
        int id = parseId(idFromGet);
        model.addAttribute("course", xmlParser.getCourseByID(id));
    }

    private int parseId(String idFromGet) {
        int id = -1;
        try {
            id = Integer.parseInt(idFromGet);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return id;
    }

}