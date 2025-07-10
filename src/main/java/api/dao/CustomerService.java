package api.dao;

import jakarta.persistence.EntityManager;
import store.entities.Customers;

public class CustomerService extends BaseRepository<Long, Customers> {

    public CustomerService(EntityManager entityManager) {
        super(Customers.class, entityManager);
    }
}
