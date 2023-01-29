package fr.loicp.simissman.service;

import fr.loicp.simissman.dto.ProjectDTO;
import fr.loicp.simissman.dto.PersonDTO;
import fr.loicp.simissman.entity.ProjectEntity;
import fr.loicp.simissman.scope.GenericScopeManager;

import java.util.List;

public interface ProjectService extends ServiceConverter<ProjectDTO, ProjectEntity> {

    List<ProjectDTO> getPersonProjects(PersonDTO person, GenericScopeManager scopeManager);

}
