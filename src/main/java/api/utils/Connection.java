package api.utils;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection {
    @Getter
    private static SessionFactory factory;

    static {
        try {
            Configuration config = new Configuration().configure();
            factory = config.buildSessionFactory();
        } catch (RuntimeException e) {
            new RuntimeException(e);
            e.printStackTrace();
        }
    }

    public static Session getSession() {
        if (factory != null){
            return factory.openSession();
        }
        return null;
    }


}
