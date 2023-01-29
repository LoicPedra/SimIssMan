package fr.loicp.simissman.repository.impl;

import fr.loicp.simissman.entity.PersonEntity;
import fr.loicp.simissman.repository.AbstractRepository;
import fr.loicp.simissman.repository.PersonRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class DefaultPersonRepository extends AbstractRepository<PersonEntity> implements PersonRepository {

    public DefaultPersonRepository() {
        super(PersonEntity.class);
    }

    @Override
    @Transactional
    public PersonEntity findUsername(String username) {
        CriteriaQuery<PersonEntity> criteriaQuery = this.getCriteriaBuilder().createQuery(this.getImplementationClass());
        Root<PersonEntity> root = criteriaQuery.from(this.getImplementationClass());
        criteriaQuery.select(root);
        criteriaQuery.where(this.getCriteriaBuilder().equal(root.get("username"), username));
        TypedQuery<PersonEntity> query = this.getEntityManager().createQuery(criteriaQuery);

        try {
            return query.setMaxResults(1).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
}
