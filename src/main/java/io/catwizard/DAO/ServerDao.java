package io.catwizard.DAO;

import io.catwizard.domain.Server;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Victor on 26-Jan-17.
 */
@Transactional
@Repository
public interface ServerDao extends CrudRepository<Server, Integer> {
}
