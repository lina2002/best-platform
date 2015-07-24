package platform;


import java.util.List;

public interface XMLParser {
    List<Course> parseXML();

    Course getCourseByID(int id);
}
