package security.corespringsecurity.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import security.corespringsecurity.domain.entity.RoleHierarchy;
import security.corespringsecurity.repository.RoleHierarchyRepository;

import java.util.Iterator;
import java.util.List;

@Service
public class RoleHierarchyServiceImpl implements RoleHierarchyService {

    @Autowired
    private RoleHierarchyRepository roleHierarchyRepository;
//    ROLE_AMDIN    parent
//    ROLE_MANAGER  ROLE_ADMIN
//    ROLE_USER     ROLE_MANAGER
    @Override
    @Transactional
    public String findAllHierarchy() {

        List<RoleHierarchy> roleHierarchies = roleHierarchyRepository.findAll();

        Iterator<RoleHierarchy> itr = roleHierarchies.iterator();

//        조건을 문자열로 반든다.
        StringBuilder concatedRoles = new StringBuilder();
        while (itr.hasNext()) {
            RoleHierarchy roleHierarchy = itr.next();
            if (roleHierarchy.getParentName() != null) {
                concatedRoles.append(roleHierarchy.getParentName().getChildName());
                concatedRoles.append(">");
                concatedRoles.append(roleHierarchy.getChildName());
                concatedRoles.append("\n");
            }
        }
//        최종 문자열로 반환
        return concatedRoles.toString();
    }
}
