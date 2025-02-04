package dev.panthu.crm.dao.Impl;

import dev.panthu.crm.dao.CustomerDAO;
import dev.panthu.crm.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOJpaImpl implements CustomerDAO {

    private final EntityManager entityManager;

    @Autowired
    public CustomerDAOJpaImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("from Customer", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer save(Customer customer) {

        return entityManager.merge(customer);
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Customer.class, id));

    }
}
