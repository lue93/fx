package br.com.lue93.fx.integrate.usecase;

import br.com.lue93.fx.domain.fiat.FiatInputData;
import br.com.lue93.fx.domain.fiat.FiatOutputData;

import br.com.lue93.fx.domain.rate.WiseRateServiceException;
import br.com.lue93.fx.application.usecase.fiat.exchange.FiatExcxhangeUseCase;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.lue93.fx.domain.fiat.Symbol.BRL;
import static br.com.lue93.fx.domain.fiat.Symbol.USD;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FiatExcxhangeUseCaseTest {

    /* Delega a responsabilidade de criacao de objetos, para o mecanismo de DI do Spring.
    *
    * Veja que as capacidades de injeção de dependência do Spring
    * para criar a instância FiatExcxhangeUseCase, que, por sua vez,
    * garante que WiseRateService e seus componentes (feignWiseClient) sejam inicializados corretamente.
    */
    @Autowired
    private FiatExcxhangeUseCase fiatExchangeUseCase;

    @Test
    public void shouldReturnFiatValueExchangedByRate() throws WiseRateServiceException {

        final FiatOutputData fiatOutputData = fiatExchangeUseCase.exec(
                new FiatInputData(USD, BRL, 2.00)
        );

        Assertions.assertInstanceOf(FiatOutputData.class, fiatOutputData);
        Assertions.assertNotEquals(0.00, fiatOutputData.fiat());

    }


}
