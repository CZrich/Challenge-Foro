package com.foro.springdoc;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(

        info = @Info(
                title = "Api-foro",
                description = "challenge foro hub Alura",
                version = "1.0.0",
                contact = @Contact(
                        name = "Richard Alberto Condo",
                        url = "https://www.linkedin.com/in/richardalbertocondozamata/",
                        email = "foro-team@gmail.com"

                )

        ),
        security = @SecurityRequirement(
                name = "Security jwt"
        )

)
@SecurityScheme(

        name = "Security jwt",
        description = "jwt authentication",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SpringDocConfiguration {


}

