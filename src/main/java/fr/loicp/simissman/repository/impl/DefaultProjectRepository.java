package fr.loicp.simissman.repository.impl;

import fr.loicp.simissman.entity.ProjectEntity;
import fr.loicp.simissman.repository.AbstractRepository;
import fr.loicp.simissman.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 * The default project repository.
 */
@Repository
@Transactional
public class DefaultProjectRepository extends AbstractRepository<ProjectEntity> implements ProjectRepository {

    /**
     * Instantiates a new default project repository instance.
     */
    public DefaultProjectRepository() {
        super(ProjectEntity.class);
    }
}
