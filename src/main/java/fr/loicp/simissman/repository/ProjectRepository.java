package fr.loicp.simissman.repository;

import fr.loicp.simissman.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The interface for project repository.
 */
@NoRepositoryBean
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
}
