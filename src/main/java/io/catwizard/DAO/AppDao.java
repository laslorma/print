package io.catwizard.DAO;

import io.catwizard.domain.App;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Victor on 16-Jan-17.
 */
@Transactional
@Repository
public interface AppDao extends CrudRepository<App, Integer>{

    @Query("select a from App a where a.domain = :domain")
    public App findByDomain(@Param("domain")String domain);

}
