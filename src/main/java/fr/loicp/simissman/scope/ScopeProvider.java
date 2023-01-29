package fr.loicp.simissman.scope;


/**
 * A scope provider.
 */
public final class ScopeProvider {

    /**
     * Provide a generic scope manager.
     *
     * @param scopes the initial scopes
     * @return the generic scope manager
     */
    public static GenericScopeManager provideScope(String... scopes) {
        return new GenericScopeManager(scopes);
    }

    /**
     * Provide an empty generic scope manager.
     *
     * @return the empty generic scope manager
     */
    public static GenericScopeManager provideEmptyScope() {
        return new GenericScopeManager();
    }

}
