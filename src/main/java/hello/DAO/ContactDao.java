package hello.DAO;

import hello.domain.Contact;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Victor on 26-Jan-17.
 */
public interface ContactDao extends CrudRepository<Contact, Integer> {
}
