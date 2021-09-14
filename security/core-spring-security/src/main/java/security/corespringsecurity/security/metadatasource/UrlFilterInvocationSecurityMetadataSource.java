package security.corespringsecurity.security.metadatasource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

//  메서드타입도도 같은 SecurityMetaDataSource를 상속받기때문에
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap = new LinkedHashMap<>();


    @Override
//    url이아닌 메서드 방식의경우 methodInvocation파라미터도 들어오기때문에 object를 파싱한다.
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

       HttpServletRequest request = ((FilterInvocation) object).getRequest();

//       key에 url 정보 value에 권한정보 일치하면 key가 일치하면 권한정보가져옴
        if (requestMap != null) {
            for(Map.Entry < RequestMatcher, List < ConfigAttribute >> entry : requestMap.entrySet()) {
                RequestMatcher matcher = entry.getKey();
                if (matcher.matches(request)) {
                    return entry.getValue();
                }
            }
        }
            return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet();

        for(Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
