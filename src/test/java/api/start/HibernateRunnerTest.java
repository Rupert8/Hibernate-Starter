package api.start;

import api.dao.CustomerService;
import api.utils.Connection;
import jakarta.persistence.EntityManagerFactory;
import lombok.Cleanup;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.junit.jupiter.api.Test;
import store.entities.*;

import java.time.LocalDate;
import java.util.Collections;


class HibernateRunnerTest {
    @Test
    public void checkManyToMany() {
        @Cleanup var session = Connection.getSession();
        session.beginTransaction();

        Users user = Users.builder()
                .username("sava")
                .firstName("viktor")
                .lastName("Predko")
                .birthDate(LocalDate.of(2006, 04, 15))
                .role(Role.ADMIN)
                .build();

        Chat chat = Chat.builder()
                .name("Group1")
                .build();

        session.save(user);
        user.addChat(chat);
        session.save(chat);

        session.getTransaction().commit();
    }

    @Test
    public void checkRepository() {
        @Cleanup var session = Connection.getSession();
        session.beginTransaction();
        CustomerService customerService = new CustomerService(session);
        customerService.save(Customers.builder()
                .firstName("Viktor")
                .lastName("Predko")
                .role(Role.ADMIN)
                .build());

        session.getTransaction().commit();
    }

//    @Test
//    public void checkOneToOne() {
//        @Cleanup var session = Connection.getSession();
//        session.beginTransaction();
//
//        Users user = Users.builder()
//                .username("sava")
//                .firstName("viktor")
//                .lastName("Predko")
//                .birthDate(LocalDate.of(2006, 04, 15))
//                .role(Role.ADMIN)
//                .build();
//
//        Profile profile = Profile.builder()
//                .user(user)
//                .street("Street")
//                .language("UK")
//                .build();
//
//        session.save(user);
//        profile.setUser(user);
//        session.save(profile);
//
//        session.getTransaction().commit();
//    }


//    @Test
//    public void checkOneToMany() {
//        @Cleanup var session = Connection.getSession();
//        session.beginTransaction();
//        session.clear();
//
//        //var company = session.get(Company.class, 1);
//        //System.out.println(company.getUsersList());
//
//
//        session.getTransaction().commit();
//    }

//    @Test // this is how hibernate works in the middle
//    public void testHibernateApi() throws SQLException, IllegalAccessException {
//        var customers = Customers.builder()
//                .id(3L)
//                .firstName("Anton")
//                .firstName("Anton")
//                .lastName("Brown")
//                .email("anton44@gmail.com").build();
//
//        String sql = """
//                Insert into %s
//                (%s)
//                values
//                (%s)
//                """;
//
//        var customerTableName = Optional.ofNullable(customers.getClass().getAnnotation(Table.class))
//                .map(table -> table.schema() + "." + table.name())
//                .orElse(customers.getClass().getName());
//
//        Field[] fields = customers.getClass().getDeclaredFields();
//
//        var columnNames = Arrays.stream(fields)
//                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                        .map(Column::name)
//                        .orElse(field.getName()))
//                .collect(Collectors.joining(","));
//
//        var valuesName = Arrays.stream(fields)
//                .map(field -> "?")
//                .collect(Collectors.joining(","));
//
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
//                "postgres", "1111");
//        var preparedStatement = connection.prepareStatement(sql.formatted(customerTableName, columnNames, valuesName));
//
//        for (int i = 0; i < fields.length; i++) {
//
//            fields[i].setAccessible(true);
//            preparedStatement.setObject(i + 1, fields[i].get(customers));
//        }
//        System.out.println(preparedStatement);
//        preparedStatement.executeUpdate();
//    }

}