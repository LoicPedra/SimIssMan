package fr.loicp.simissman.controller;

import fr.loicp.simissman.context.PersonContext;
import fr.loicp.simissman.dto.PersonDTO;
import fr.loicp.simissman.form.AuthenticationForm;
import fr.loicp.simissman.service.PersonService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Collections;

@Controller
public class AuthenticationController extends AbstractController {

    @Autowired
    private PersonService personService;

    @GetMapping("/login")
    public String displayLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String performLogin(@ModelAttribute AuthenticationForm form, HttpServletResponse response, Model model)
            throws IOException {
        this.getSession().setAttribute("username", form.getUsername());
        PersonDTO person = personService.findByUsername(form.getUsername());
        if (person == null) {
            this.getSession().removeAttribute("username");
            model.addAttribute("errors", Collections.singleton("invalid.credentials"));
            return this.displayLogin();
        }
        response.sendRedirect("/");
        return null;
    }

    @GetMapping("/logout")
    public String displayLogout(HttpServletResponse response) throws IOException {
        this.getSession().removeAttribute("username");
        PersonContext.clear();
        response.sendRedirect("/");
        return null;
    }

}
