package fr.loicp.simissman.context;

import fr.loicp.simissman.dto.PersonDTO;

/**
 * The person context.
 */
public class PersonContext {
    private static final ThreadLocal<PersonDTO> CONTEXT = new ThreadLocal<>();

    /**
     * Sets person.
     *
     * @param person the person
     */
    public static void setPerson(PersonDTO person) {
        CONTEXT.set(person);
    }

    /**
     * Gets person.
     *
     * @return the person
     */
    public static PersonDTO getPerson() {
        return CONTEXT.get();
    }

    /**
     * Clear.
     */
    public static void clear() {
        CONTEXT.remove();
    }
}
