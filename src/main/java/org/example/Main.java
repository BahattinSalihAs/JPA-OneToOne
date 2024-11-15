package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Passport;
import org.example.entities.Person;
import org.example.entities.User;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String puName = "pu-name";
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true");
        props.put("hibernate.hbm2ddl.auto","create");
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName),props);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Passport passport = new Passport();
            passport.setNumber("ABC123");
            Person person = new Person();
            person.setName("John");
            person.setPassport(passport);

            em.persist(person);

            User user = new User();
            user.setName("Ahmed");
            user.setDescription("Some another user");
            em.persist(user);

            em.getTransaction().commit();
        }finally {
            em.close();
        }

    }
}