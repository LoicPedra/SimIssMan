package fr.loicp.simissman.entity;


/**
 * The interface for unique constraint for entity.
 */
public interface UniqueEntity {

    /**
     * Gets unique id.
     *
     * @return the unique id
     */
    Long getUniqueId();

    /**
     * Gets unique id.
     *
     * @param uniqueId the unique id
     */
    void getUniqueId(Long uniqueId);

}
