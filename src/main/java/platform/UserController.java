package platform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @RequestMapping("/homepage")
    public void homepage(final Model model, final HttpServletRequest request) {
        setUsernameAttribute(model, request);
    }

    private void setUsernameAttribute( final Model model, final HttpServletRequest request) {
        model.addAttribute("username", request.getRemoteUser());
    }

}
