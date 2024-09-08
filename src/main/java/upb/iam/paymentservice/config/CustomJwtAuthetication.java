package upb.iam.paymentservice.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.List;

public class CustomJwtAuthetication implements Converter<Jwt, CustomJwt> {
    @Override
    public CustomJwt convert(Jwt source) {
        List<String> authorities = (List<String>) source.getClaims().get("authorities");
        CustomJwt authenticationObj =
                new CustomJwt(source, authorities.stream().map(SimpleGrantedAuthority::new).toList());

        return authenticationObj;
    }
}
