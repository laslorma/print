package hello.DAO;

import hello.domain.Payment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Victor on 26-Jan-17.
 */
public interface PaymentDao extends CrudRepository<Payment, Integer> {
}
