import org.junit.Test;
import platform.Course;
import platform.XMLParserImpl;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class XMLParserImplTest {
    private XMLParserImpl xmlParser = new XMLParserImpl("test.xml");

    private List<Course> courses = xmlParser.parseXML();

    @Test
    public void shouldParseNameOfTheCourse() throws Exception {
        Course course = courses.get(0);
        assertEquals("TDD", course.getName());
    }

    @Test
    public void shouldParseDescriptionOfTheCourse() throws Exception {
        Course course = courses.get(0);
        assertEquals("Learn best practices of test driven development", course.getDescription());
    }

    @Test
    public void shouldParseIdOfTheThirdCourse() throws Exception {
        Course course = courses.get(2);
        assertEquals(3, course.getId());
    }
}
