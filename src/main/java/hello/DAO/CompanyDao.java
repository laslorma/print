package hello.DAO;


import hello.domain.Company;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Victor on 26-Jan-17.
 */
public interface CompanyDao extends CrudRepository<Company, Integer> {
}
