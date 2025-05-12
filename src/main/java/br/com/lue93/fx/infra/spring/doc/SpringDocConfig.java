package br.com.lue93.fx.infra.spring.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI myOpenApiConfig() {

        Contact contact = new Contact();
        contact.setEmail("eduardo.ungarattolima@gmail.com");
        contact.setName("Eduardo");
        contact.setUrl("https://github.com/ungaratto93");
        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("API de Cambio")
                .version("1.0.0")
                .contact(contact)
                .description("API **SEM FINS COMERCIAIS** para realizar calculo cambial com base nas taxas fornecidas pela WISE.")
                .termsOfService("")
                .license(mitLicense);


        return new OpenAPI().info(info);

    }

}
