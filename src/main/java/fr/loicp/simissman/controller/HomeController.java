package fr.loicp.simissman.controller;

import fr.loicp.simissman.context.PersonContext;
import fr.loicp.simissman.scope.ScopeProvider;
import fr.loicp.simissman.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends AbstractController {
    @Autowired
    private ProjectService service;

    @GetMapping("/")
    public String displayHome(Model model) {
        model.addAttribute("projects", this.service.getPersonProjects(null, ScopeProvider.provideEmptyScope()));
        return "home";
    }

}
