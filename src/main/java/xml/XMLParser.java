package xml;


import platform.Course;

import java.util.List;

public interface XMLParser {
    List<Course> parseXML();

    Course getCourseByID(int id);
}
