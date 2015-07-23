package platform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CoursesController {
    private final String inputFileName = "courses.xml";
    private XMLParser xmlParser = new XMLParserImpl(inputFileName);

    @RequestMapping("/")
    public String index() {
        return "redirect:/courses";
    }

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
    public void course(@RequestParam(value = "id", required = false) String courseId, Model model) {
        addCourse(courseId, model);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public void formForCourseEditing(@RequestParam(value = "id", required = false) String courseId, Model model) {
        model.addAttribute("courseHasBeenSavedInfo", "");
        addCourse(courseId, model);
    }

    private void addCourse(String courseId, Model model) {
        int id = parseId(courseId, -1);
        model.addAttribute("course", xmlParser.getCourseByID(id));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void submitEditedCourse(@ModelAttribute("course") Course course, Model model) {
        XMLEditor xmlEditor = new XMLEditorImpl(inputFileName);
        xmlEditor.update(course);
        model.addAttribute("courseHasBeenSavedInfo", "Course has been saved.");
    }

    @RequestMapping("/login")
    public void login(Model model) {

    }

    private int parseId(String idFromGet, int defaultValue) {
        int id = defaultValue;
        if (idFromGet != null) {
            try {
                id = Integer.parseInt(idFromGet);
            } catch (NumberFormatException e) {
            }
        }
        return id;
    }

}
