package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(School.class)
                .addAnnotatedClass(Principal.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            Transaction transaction = session.beginTransaction();
//            /**
//             * С помощью Hibernate получите любого директора, а затем получите его
//             * школу.
//             */
//            Principal somePrincipal = session.get(Principal.class, 1);
//            System.out.println(somePrincipal);
//
//            /**
//             * Получите любую школу, а затем получите ее директора.
//             */
//            School someSchool = session.get(School.class, 2);
//            System.out.println(someSchool);

            /**
             * Создайте нового директора и новую школу и свяжите эти сущности.
             */
            Principal newPrincipal = new Principal("Ivan", 33);
            School newSchool = new School(32, newPrincipal);

            session.persist(newPrincipal);
            session.persist(newSchool);

            /**
             * Смените директора у существующей школы.
             */
            Principal updatePrincipal = session.get(Principal.class, 3);
            updatePrincipal.setName("Tobi");


            transaction.commit();
            session.close();
        } finally {
            sessionFactory.close();
        }
    }
}
