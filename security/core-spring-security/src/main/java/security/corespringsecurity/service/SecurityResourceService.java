package security.corespringsecurity.service;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import security.corespringsecurity.domain.entity.Resources;
import security.corespringsecurity.repository.AccessIpRepository;
import security.corespringsecurity.repository.ResourcesRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityResourceService {

    private ResourcesRepository resourcesRepository;

    private AccessIpRepository accessIpRepository;

    public SecurityResourceService(ResourcesRepository resourcesRepository, AccessIpRepository accessIpRepository) {
        this.accessIpRepository = accessIpRepository;
        this.resourcesRepository = resourcesRepository;
    }

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resourceList = resourcesRepository.findAllResources();
//        권한이 여러개있을수있기에 각 role정보를 SecurityConfig타입으로 넣는다.
        resourceList.forEach(resources -> {
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            resources.getRoleSet().forEach(role -> {
                configAttributes.add(new SecurityConfig(role.getRoleName()));
                result.put(new AntPathRequestMatcher(resources.getResourceName()), configAttributes);
            });
        });
        return result;
    }

    public List<String> getAccesIpList() {
        List<String> accessIpList = accessIpRepository.findAll().stream().map(accessIp -> accessIp.getIpAddress()).collect(Collectors.toList());

        return accessIpList;
    }
}
