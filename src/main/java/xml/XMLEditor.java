package xml;

import platform.Course;

public interface XMLEditor {
    void update(Course course);

    void remove(int courseId);

    void add(Course course);
}
