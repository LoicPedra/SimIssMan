package fr.loicp.simissman.repository;

import fr.loicp.simissman.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The interface for person repository.
 */
@NoRepositoryBean
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    /**
     * Find person entity by username.
     *
     * @param username the username
     * @return the person entity
     */
    PersonEntity findUsername(String username);

}
