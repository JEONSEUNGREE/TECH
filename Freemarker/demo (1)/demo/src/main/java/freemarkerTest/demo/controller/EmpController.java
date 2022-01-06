package freemarkerTest.demo.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarkerTest.demo.domain.Employee;
import freemarkerTest.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@RestController
public class EmpController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    Configuration configuration;


    @PostMapping("/saveEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee, Map<String, Object> model) throws MessagingException, TemplateException, IOException {

        model.put("name", employee.getEmp_name());
        model.put("address", employee.getEmp_address());

        sendmail(employee, model);

        employeeRepository.save(employee);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    private void sendmail(Employee employee, Map<String, Object> model) throws MessagingException, IOException, TemplateException {

        final String emp_email = employee.getEmp_email();
        final String emailSubject = "Successfully Registration with MyProgrammingTask";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED);

        helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

        Template template = configuration.getTemplate("email-template.ftl");

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setTo(emp_email);
        helper.setText(html, true);
        helper.setSubject(emailSubject);

        javaMailSender.send(mimeMessage);
    }
}
