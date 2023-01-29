package fr.loicp.simissman.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The Abstract controller.
 */
public class AbstractController {

    @Autowired
    private HttpSession session;

    /**
     * Gets HTTP session.
     *
     * @return the session
     */
    public HttpSession getSession() {
        return session;
    }
}
