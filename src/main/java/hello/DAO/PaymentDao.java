package hello.DAO;

import hello.domain.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Victor on 26-Jan-17.
 */
@Transactional
@Repository
public interface PaymentDao extends CrudRepository<Payment, Integer> {
}
