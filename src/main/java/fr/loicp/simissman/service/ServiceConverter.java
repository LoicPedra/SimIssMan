package fr.loicp.simissman.service;

import fr.loicp.simissman.scope.GenericScopeManager;
import fr.loicp.simissman.scope.ScopeProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The interface for Service converter.
 *
 * @param <D> the type of DTO object
 * @param <E> the type of Entity object
 */
public interface ServiceConverter<D, E> {

    /**
     * Convert a DTO to entity.
     *
     * @param dto the dto
     * @return the entity
     */
    E convertToEntity(D dto);

    /**
     * Convert an entity to a DTO.
     *
     * @param entity the entity
     * @return the DTO
     */
    default D convertToDto(E entity) {
        return convertToDto(entity, ScopeProvider.provideScope());
    }

    /**
     * Convert an entity to a scoped DTO.
     *
     * @param entity       the entity
     * @param scopeManager the scope manager
     * @return the scope DTO
     */
    D convertToDto(E entity, GenericScopeManager scopeManager);

    /**
     * Convert a list of DTO to an entity list.
     *
     * @param dtoList the DTO list
     * @return the list of entities
     */
    default List<E> convertToEntityList(List<D> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return Collections.emptyList();
        }
        return dtoList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    /**
     * Convert an entity list to a DTO list.
     *
     * @param entities the entities
     * @return the DTO list
     */
    default List<D> convertToDtoList(List<E> entities) {
        return convertToDtoList(entities, ScopeProvider.provideScope());
    }

    /**
     * Convert an entity list to a scoped DTO list.
     *
     * @param entities     the entities
     * @param scopeManager the scope manager
     * @return the scoped DTO list
     */
    default List<D> convertToDtoList(Iterable<E> entities, GenericScopeManager scopeManager) {
        List<D> dtoList = new ArrayList<>();
        if (entities == null) {
            return dtoList;
        }
        for (E entity : entities) {
            D dto = convertToDto(entity, scopeManager);
            if (dto != null) {
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
}
