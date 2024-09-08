package upb.iam.paymentservice.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
@Getter
@Setter
public class DemoEndpoint {
    @Value("${payment-service.test}")
    private String test;

    @Value("${keySetUri.value}")
    private String value;

    @Value("${spring.datasource.url}")
    private String datasource;

    @GetMapping
    public List<String> demo() {
        return List.of(value, datasource, test);
    }
}
