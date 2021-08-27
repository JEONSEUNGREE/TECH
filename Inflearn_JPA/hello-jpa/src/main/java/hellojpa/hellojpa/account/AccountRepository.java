package hellojpa.hellojpa.account;

import hellojpa.hellojpa.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
