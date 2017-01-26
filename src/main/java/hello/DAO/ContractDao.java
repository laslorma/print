package hello.DAO;

import hello.domain.Contract;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Victor on 26-Jan-17.
 */
public interface ContractDao extends CrudRepository<Contract, Integer> {
}
