package io.catwizard.DAO;

import io.catwizard.domain.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Victor on 26-Jan-17.
 */
@Transactional
@Repository
public interface ContactDao extends CrudRepository<Contact, Integer> {
}
