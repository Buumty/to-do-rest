package com.wojciechandrzejczak.to_do_rest;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataBaseResetService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void resetAutoIncrement() {
        // Disable safe updates
        entityManager.createNativeQuery("SET SQL_SAFE_UPDATES = 0;").executeUpdate();

        // Update IDs to sequential values starting from 1
        // Execute session variable update separately
        entityManager.createNativeQuery("SET @num := 0;").executeUpdate();
        entityManager.createNativeQuery("UPDATE new_schema.task SET id = (@num := @num + 1);").executeUpdate();

        // Reset the auto-increment value to 1
        entityManager.createNativeQuery("ALTER TABLE new_schema.task AUTO_INCREMENT = 1;").executeUpdate();
    }
}