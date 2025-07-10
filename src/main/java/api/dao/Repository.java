package api.dao;

import store.entities.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable, E extends BaseEntity<K>> {
    Optional<E> findById(K id);

    void update(E e);

    boolean delete(K id);

    void save(E e);

    List<E> findAll();

}
