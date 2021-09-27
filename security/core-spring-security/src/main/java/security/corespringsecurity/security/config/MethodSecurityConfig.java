package security.corespringsecurity.security.config;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import security.corespringsecurity.security.factory.MethodResourcesFactoryBean;
import security.corespringsecurity.security.processor.ProtectPointcutPostProcessor;
import security.corespringsecurity.service.SecurityResourceService;


@Configuration
@EnableGlobalMethodSecurity
// map기반이기때문에서 따로 어노테이션관련 설정은 주지않는다. false
// 물론 prePost , secured , jsr250 true로 주고 같이 써도 상관은 없음
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private SecurityResourceService securityResourceService;



    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        return new MapBasedMethodSecurityMetadataSource();
    }
//  method와 pointcut 표현식을 파싱해서 보안이필요하고 프록시 대상이될만한 빈들을 찾아서 클래스정보와 메서드 정보를 추출해서
//    MapBaesMethodSecurityMetatdataSource에 전달해야함. 그 클래스가 ProtectPointcutPostProcessor - 빈후 처리기 BeanPostProcessor
//    BeanPostProcessor 빈이 생성된 이후에 그빈의 초기화 이전,이후 단계에서 빈을 검사하고 빈의 설정을 변경조작할수있도록하는 인터페이스

    @Bean
    public MapBasedMethodSecurityMetadataSource mapBasedMethodSecurityMetadataSource() throws Exception {

        return new MapBasedMethodSecurityMetadataSource(methodResourcesMapFactoryBean().getObject());
    }

    @Bean
    public MethodResourcesFactoryBean methodResourcesMapFactoryBean() {

        MethodResourcesFactoryBean methodResourcesFactoryBean = new MethodResourcesFactoryBean();
        methodResourcesFactoryBean.setSecurityResourceService(securityResourceService);
        return methodResourcesFactoryBean;
    }

    @Bean
    public ProtectPointcutPostProcessor protectPointcutPostProcessor() throws Exception {
        ProtectPointcutPostProcessor protectPointcutPostProcessor = new ProtectPointcutPostProcessor(mapBasedMethodSecurityMetadataSource());

//        protectPointcutPostProcessor.setPointcutMap(pointCutResourcesMapFactoryBean().getObjtec());
        return protectPointcutPostProcessor;
    }

}
