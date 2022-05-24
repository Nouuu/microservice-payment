package org.openapitools.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-05-24T21:19:14.382432600+02:00[Europe/Paris]")
@Controller
@RequestMapping("${openapi.boissiPay.base-path:}")
public class PaymentApiController implements PaymentApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PaymentApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
