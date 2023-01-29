package fr.loicp.simissman.service.impl;

import fr.loicp.simissman.dto.PersonDTO;
import fr.loicp.simissman.entity.PersonEntity;
import fr.loicp.simissman.repository.PersonRepository;
import fr.loicp.simissman.scope.GenericScopeManager;
import fr.loicp.simissman.service.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DefaultPersonService implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    @Transactional
    public PersonDTO findByUsername(String username) {
        return this.convertToDto(this.repository.findUsername(username));
    }

    @Override
    public PersonEntity convertToEntity(PersonDTO dto) {
        return null;
    }

    @Override
    public PersonDTO convertToDto(PersonEntity entity, GenericScopeManager scopeManager) {
        if (entity == null) {
            return null;
        }
        PersonDTO person = new PersonDTO();
        person.setUsername(entity.getUsername());
        return person;
    }
}
