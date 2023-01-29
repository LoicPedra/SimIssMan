package fr.loicp.simissman.util;

import jakarta.servlet.http.HttpSession;

/**
 * Session utils.
 */
public final class SessionUtil {

    /**
     * Gets attribute and cast to target object type.
     *
     * @param <T>     the type parameter
     * @param session the session
     * @param name    the name
     * @param c       the targeted class type
     * @return the attribute or any if missing
     */
    public static <T> T getAttribute(HttpSession session, String name, Class<T> c) {
        Object attribute = session.getAttribute(name);
        if (attribute == null) {
            return null;
        }
        return c.cast(attribute);
    }
}
