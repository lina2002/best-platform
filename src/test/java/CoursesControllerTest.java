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
    private CoursesController coursesController = new CoursesController();
    ArgumentCaptor<Course> argument = ArgumentCaptor.forClass(Course.class);

    @Test
    public void shouldReturnNoSuchCourseIDWhenNonExistingIDIsGiven() throws Exception {
        coursesController.course("-120", model);
        verify(model).addAttribute(anyString(),argument.capture());
        assertEquals(-1, argument.getValue().getId());
    }

    @Test
    public void shouldReturnNoSuchCourseIDWhenNullAsIDIsGiven() throws Exception {
        coursesController.course(null, model);
        verify(model).addAttribute(anyString(),argument.capture());
        assertEquals(-1, argument.getValue().getId());
    }
}
