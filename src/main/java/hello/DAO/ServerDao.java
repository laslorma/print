package hello.DAO;

import hello.domain.Server;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Victor on 26-Jan-17.
 */
public interface ServerDao extends CrudRepository<Server, Integer> {
}
