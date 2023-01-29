package fr.loicp.simissman.filter;

import fr.loicp.simissman.context.PersonContext;
import fr.loicp.simissman.dto.PersonDTO;
import fr.loicp.simissman.service.PersonService;
import fr.loicp.simissman.util.SessionUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;

@Component
@Order(1)
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private HttpSession session;

    @Autowired
    private PersonService personService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = new UrlPathHelper().getPathWithinApplication(httpRequest);
        String person = SessionUtil.getAttribute(session, "username", String.class);

        if ("/login".equals(path) && person != null) {
            httpResponse.sendRedirect("/");
            return;
        } else if (!"/login".equals(path) && person == null) {
            httpResponse.sendRedirect("/login");
            return;
        }

        if (person != null) {
            PersonDTO personByUsername = personService.findByUsername(person);
            if (!"/logout".equals(path) && personByUsername == null) {
                session.removeAttribute("username");
                httpResponse.sendRedirect("/login");
                return;
            }
            PersonContext.setPerson(personByUsername);
        } else {
            PersonContext.clear();
        }

        filterChain.doFilter(httpRequest, httpResponse);
    }
}
