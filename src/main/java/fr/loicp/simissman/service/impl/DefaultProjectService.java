package fr.loicp.simissman.service.impl;

import fr.loicp.simissman.dto.ProjectDTO;
import fr.loicp.simissman.dto.UserDTO;
import fr.loicp.simissman.entity.PersonEntity;
import fr.loicp.simissman.entity.ProjectEntity;
import fr.loicp.simissman.entity.ProjectIssueEntity;
import fr.loicp.simissman.repository.ProjectRepository;
import fr.loicp.simissman.scope.GenericScopeManager;
import fr.loicp.simissman.service.ProjectService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DefaultProjectService implements ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProjectService.class);

    @Autowired
    private ProjectRepository repository;

    @Override
    @Transactional
    public List<ProjectDTO> getUserProjects(UserDTO user, GenericScopeManager scopeManager) {
        return this.convertToDtoList(this.repository.findAll(), scopeManager);
    }

    @Override
    public ProjectEntity convertToEntity(ProjectDTO dto) {
        return null;
    }

    @Override
    public ProjectDTO convertToDto(ProjectEntity entity, GenericScopeManager scopeManager) {
        if (entity == null) {
            return null;
        }
        ProjectDTO dto = new ProjectDTO();
        dto.setId(entity.getUniqueId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        if (scopeManager.hasScope("members")) {
            Set<PersonEntity> members = entity.getMembers();
            LOGGER.info("Log members: {}", members);
        }
        if (scopeManager.hasScope("issues")) {
            Set<ProjectIssueEntity> issues = entity.getIssues();
            LOGGER.info("Log issues: {}", issues);
        }
        return dto;
    }
}
