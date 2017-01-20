package hello.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Victor on 16-Jan-17.
 */
@Transactional
@Repository
public interface AppDao extends CrudRepository<App, Integer>{

    public App findByDomain(String email);
}
