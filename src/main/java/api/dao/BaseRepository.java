package api.dao;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import store.entities.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseRepository<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E>{

    private final Class<E> entityClass;
    @Getter
    private final EntityManager entityManager;

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public void update(E e) {
        entityManager.merge(e);
    }

    @Override
    public boolean delete(K id) {
        try{
            entityManager.remove(entityManager.find(entityClass, id));
            entityManager.flush();
            return true;
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void save(E e) {
        entityManager.persist(e);
    }

    @Override
    public List<E> findAll() {
        var criteriaBuilder = entityManager.getCriteriaBuilder().createQuery(entityClass);
        criteriaBuilder.from(entityClass);
        return entityManager.createQuery(criteriaBuilder).getResultList();
    }
}
