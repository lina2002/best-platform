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
    private final XMLParser xmlParser = new XMLParserImpl(inputFileName);
    private final XMLEditor xmlEditor = new XMLEditorImpl(inputFileName);

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
        addCourseToModel(courseId, model);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public void formForCourseEditing(@RequestParam(value = "id", required = false) String courseId, Model model) {
        model.addAttribute("courseHasBeenSavedInfo", "");
        addCourseToModel(courseId, model);
    }

    private void addCourseToModel(String courseId, Model model) {
        int id = parseId(courseId, -1);
        model.addAttribute("course", xmlParser.getCourseByID(id));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void submitEditedCourse(@ModelAttribute("course") Course course, Model model) {
        xmlEditor.update(course);
        model.addAttribute("courseHasBeenSavedInfo", "Course has been saved.");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void formForAddingCourse(Model model) {
        model.addAttribute("courseHasBeenSavedInfo", "");
        model.addAttribute("course", new Course());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void submitAddedCourse(@ModelAttribute("course") Course course, Model model) {
        xmlEditor.add(course);
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
