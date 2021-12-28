package freemarkerTest.demo.controller;

import freemarkerTest.demo.config.FreemarkerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@Slf4j
public class HelloFreemarker {

    //  만일 #include 문법으로 여러 ftl을 vue의 component처럼 나누어 사용하는 경우
//  하나의 teamplate에서 오류가 발생하면 전체 페이지에 반영이 되어버린다.
//    그래서 pre-compile을 통해 에러발생시 대체하고 성공시 추가할수있도록할 필요가 있다.
    @RequestMapping(value = "/high/error")
    public String highError(Model model) {
        model.addAttribute("msg", null);
        return "index";
    }


    @RequestMapping(value="/high/solve")
    public String highSolve(Model model) throws Exception {
//      템플릿에 주입할 데이터를 담을 데이터 모델을 작성한다. Map<String, Object>, POJO 인스턴스 모두 사용이 가능하다.
//      데이터 모델의 가장 간단한 방법은 해시맵을 통해 트리 형태로 데이터 모델을 만드는 것이다.
        Map<String, Object> highData = new LinkedHashMap<>();
        highData.put("msg", null);

        model.addAttribute("high", template("high", highData));
        model.addAttribute("low", template("low", new LinkedHashMap<>()));
        return "index2";
    }
// 예외처리를 하기위함
//  하나의 ftl파일에서 오류 발생시 긴 오류 메시지가 출력되기때문에 이를 방지하기위해 try-cath문 사용
//   에러시 return 빈 string 출력

//    템플릿 처리 로직 작성: Vanilla Java
//    freemarker.template.Configuration 인스턴스는 Freemarker의 사용을 위한 시작점이 되는 인스턴스이다.
//    전반적인 환경 설정과 캐시 처리를 담당하는데 무거운 인스턴스이므로 반드시 싱글턴 객체로 취급하여 재사용해야 한다
   private String template(String templateName, Map<String, Object> data) {
//      template Loader
        try {
//           /template 디렉토리에 있는 templateName의 freemarker템플릿파일을 로드한다.
            Template template = FreemarkerConfig.newFreeMarkerConfig().getTemplate(templateName + ".ftl");
            Writer out = new StringWriter();
//           Map 컬렉션을 이용해서 템플릿 처리 (트리형태)
//           process 메서드에 의해 처리됨
            template.process(data, out);
//            쓰고 닫아주기
            out.close();
            return out.toString();
        }catch (Exception e){
            return "";
        }
    }

}
