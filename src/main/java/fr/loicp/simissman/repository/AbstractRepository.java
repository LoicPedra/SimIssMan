package fr.loicp.simissman.repository;

import fr.loicp.simissman.entity.UniqueEntity;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * The Abstract repository.
 *
 * @param <T> the type parameter
 */
@Transactional
public abstract class AbstractRepository<T extends UniqueEntity> implements CrudRepository<T, Long> {

    /**
     * The constant LOGGER.
     */
    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> implementationClass;

    private CriteriaBuilder criteriaBuilder;

    /**
     * Instantiates a new Abstract repository instance.
     *
     * @param implementationClass the implementation class
     */
    public AbstractRepository(Class<T> implementationClass) {
        this.implementationClass = implementationClass;
    }

    /**
     * Save s.
     *
     * @param <S>    the type parameter
     * @param entity the entity
     * @return the s
     */
    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        if (entity.getUniqueId() == null) {
            this.getEntityManager().persist(entity);
        }
        return this.entityManager.merge(entity);
    }

    /**
     * Save all iterable.
     *
     * @param <S>      the type parameter
     * @param entities the entities
     * @return the iterable
     */
    @Override
    @Transactional
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entity: entities) {
            this.save(entity);
        }
        return entities;
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    @Override
    @Transactional
    public Optional<T> findById(Long id) {
        CriteriaQuery<T> criteriaQuery = this.getCriteriaBuilder().createQuery(this.implementationClass);
        Root<T> root = criteriaQuery.from(this.implementationClass);
        criteriaQuery.select(root);
        criteriaQuery.where(this.getCriteriaBuilder().equal(root.get("id"), id));
        TypedQuery<T> query = this.getEntityManager().createQuery(criteriaQuery);

        try {
            T singleResult = query.setMaxResults(1).getSingleResult();
            return Optional.of(singleResult);
        } catch (NoResultException | NonUniqueResultException e) {
            LOGGER.error("Cannot find entity by id", e);
        }
        return Optional.empty();
    }

    /**
     * Exists by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    @Override
    @Transactional
    public boolean existsById(Long id) {
        return this.findById(id).isPresent();
    }

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    @Override
    @Transactional
    public Iterable<T> findAll() {
        CriteriaQuery<T> criteriaQuery = this.getCriteriaBuilder().createQuery(this.implementationClass);
        Root<T> root = criteriaQuery.from(this.implementationClass);
        criteriaQuery.select(root);
        TypedQuery<T> query = this.getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * Find all by id iterable.
     *
     * @param ids the ids
     * @return the iterable
     */
    @Override
    @Transactional
    public Iterable<T> findAllById(Iterable<Long> ids) {
        CriteriaQuery<T> criteriaQuery = this.getCriteriaBuilder().createQuery(this.implementationClass);
        Root<T> root = criteriaQuery.from(this.implementationClass);
        criteriaQuery.select(root);
        criteriaQuery.where(root.get("id").in(ids));
        TypedQuery<T> query = this.getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * Count long.
     *
     * @return the long
     */
    @Override
    @Transactional
    public long count() {
        CriteriaQuery<Long> criteriaQuery = this.getCriteriaBuilder().createQuery(Long.class);
        Root<T> root = criteriaQuery.from(this.implementationClass);
        criteriaQuery.select(this.getCriteriaBuilder().count(root));
        return this.getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }

    /**
     * Delete by id.
     *
     * @param id the id
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<T> entity = this.findById(id);
        entity.ifPresent(this::delete);
    }

    /**
     * Delete.
     *
     * @param entity the entity
     */
    @Override
    @Transactional
    public void delete(T entity) {
        this.getEntityManager().remove(entity);
    }

    /**
     * Delete all by id.
     *
     * @param longs the longs
     */
    @Override
    @Transactional
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete all.
     *
     * @param entities the entities
     */
    @Override
    @Transactional
    public void deleteAll(Iterable<? extends T> entities) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete all.
     */
    @Override
    @Transactional
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets entity manager.
     *
     * @return the entity manager
     */
    protected final EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Gets criteria builder.
     *
     * @return the criteria builder
     */
    protected final CriteriaBuilder getCriteriaBuilder() {
        if (criteriaBuilder == null) {
            criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
        }
        return criteriaBuilder;
    }
}
