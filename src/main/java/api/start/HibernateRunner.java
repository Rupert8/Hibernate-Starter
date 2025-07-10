package api.start;

import api.utils.Connection;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import store.entities.Company;
import store.entities.Users;
import store.entities.Role;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {


//        System.out.println(dao.delete(14L));
//        log.info("DeleteInfo: {}" , dao.delete(11L));

        @Cleanup var session = Connection.getSession();
        session.beginTransaction();

        var company = session.get(Company.class, 1);

        Users users = Users.builder()
                .username("Sava1")
                .firstName("Ivan1")
                .lastName("Predko1")
                .birthDate(LocalDate.of(2000, 1, 15))
                .role(Role.ADMIN)
                .company(company)
                .build();

        session.save(users);

        session.getTransaction().commit();
    }
}
