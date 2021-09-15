package security.corespringsecurity.security.voter;


import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import security.corespringsecurity.service.SecurityResourceService;

import java.util.Collection;
import java.util.List;

public class IpAddressVoter implements AccessDecisionVoter {

    private SecurityResourceService securityResourceService;

    public IpAddressVoter(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection collection) {

//        사용자의 정보로부터 ip정보를 가져온다.
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();

//        Block한 Ip정보를 가져온다.
        List<String> accesIpList = securityResourceService.getAccesIpList();

        int result = ACCESS_DENIED;
//      비교한다.
        for (String ipAddress : accesIpList) {
            if (remoteAddress.equals(ipAddress)) {
//                granted로 줘버리면 vote를 그냥 통과하도록 두는거여서 사용자가 접속하고자하는자원에 인가처리가
//                제대로 이루어지지않고 접근해버리기에 ABSTAIN을 준어 인가를 따로 심의하도록한다.
                return ACCESS_ABSTAIN;
            }

//            다른 심의로 넘어가지않도록 예외 발생
            if (result == ACCESS_DENIED) {
                throw new AccessDeniedException("Invalid IpAddress");
            }
        }
        return result;
    }

    @Override
    public boolean supports(Class clazz) {
        return false;
    }
}
