package com.manning.javapersistance.hello_world;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HelloWorldJPATest {
    //@Test
    public void storedLoadMessage() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello_world_unit");

        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Message message = new Message();
            message.setText("Hello World!");

            em.persist(message);

            em.getTransaction().commit();

            em.getTransaction().begin();

            final List<Message> messages = em.createQuery("Select m from Message m", Message.class).getResultList();

            messages.get(messages.size() - 1).setText("Hello World from JPA!");

            em.getTransaction().commit();

//            assertAll(
//                    () -> assertEquals(1, messages.size()),
//                    () -> assertEquals("Hello World from JPA!", messages.get(0).getText());
//            );

            em.close();
        }
        finally {
            emf.close();
        }
    }

}
