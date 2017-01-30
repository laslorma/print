package hello.DAO;


import hello.domain.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Victor on 26-Jan-17.
 */
@Transactional
@Repository
public interface CompanyDao extends CrudRepository<Company, Integer> {

    @Query("select a from Company a where a.name = :name")
    public Company findByName(@Param("name")String name);
}
