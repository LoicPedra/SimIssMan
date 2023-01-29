package fr.loicp.simissman.service;

import fr.loicp.simissman.dto.PersonDTO;
import fr.loicp.simissman.entity.PersonEntity;

/**
 * The interface Person service.
 */
public interface PersonService extends ServiceConverter<PersonDTO, PersonEntity> {

    /**
     * Find by username person dto.
     *
     * @param username the username
     * @return the person dto
     */
    PersonDTO findByUsername(String username);

}
