package fr.loicp.simissman.scope;

import java.util.Set;

/**
 * The generic scope manager.
 */
public final class GenericScopeManager {

    private final Set<String> scopes;

    /**
     * Instantiates a new generic scope manager instance.
     *
     * @param scopes the initial scopes
     */
    GenericScopeManager(String... scopes) {
        this.scopes = Set.of(scopes);
    }

    /**
     * Returns whether the manager contains the given scope or is not scoped.
     *
     * @param scope the scope
     * @return the result
     */
    public boolean hasScope(String scope) {
        return scopes.isEmpty() || scopes.contains(scope);
    }
}
