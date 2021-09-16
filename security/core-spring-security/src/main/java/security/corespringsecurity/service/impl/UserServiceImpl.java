package security.corespringsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import security.corespringsecurity.domain.Account;
import security.corespringsecurity.repository.UserRepository;
import security.corespringsecurity.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Account account) {

        userRepository.save(account);
    }

    @Override
    @Secured("ROLE_MANAGER")
    public void order() {
        System.out.println("order");
    }
}
