import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import platform.Course;
import platform.CoursesController;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CoursesControllerTest {
    @Mock Model model;

    @Test
    public void shouldReturnNoSuchCourseIDWhenNonExistingIDIsGiven() throws Exception {
        CoursesController coursesController = new CoursesController();
        coursesController.course("-120", model);
        ArgumentCaptor<Course> argument = ArgumentCaptor.forClass(Course.class);
        verify(model).addAttribute(anyString(),argument.capture());
        assertEquals(-1, argument.getValue().getId());
    }

    @Test
    public void shouldReturnNoSuchCourseIDWhenNullAsIDIsGiven() throws Exception {
        CoursesController coursesController = new CoursesController();
        coursesController.course(null, model);
        ArgumentCaptor<Course> argument = ArgumentCaptor.forClass(Course.class);
        verify(model).addAttribute(anyString(),argument.capture());
        assertEquals(-1, argument.getValue().getId());
    }
}
