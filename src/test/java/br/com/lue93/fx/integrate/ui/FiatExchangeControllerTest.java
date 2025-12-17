package br.com.lue93.fx.integrate.ui;

import br.com.lue93.fx.domain.fiat.vo.FiatInputData;
import br.com.lue93.fx.domain.fiat.entities.Symbol;
import br.com.lue93.fx.application.services.rate.WiseRateServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.lue93.fx.adapters.controllers.FiatExchangeController;


@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
 class FiatExchangeControllerTest {

    @Autowired
    FiatExchangeController controller;


    @Test
    void shouldReturnFiatValueConvertedWhenRequestWithValidCurrenciesAndAmount() throws WiseRateServiceException {

        final var fiatOutputDataResponseEntity = controller.post(
                new FiatInputData(Symbol.USD, Symbol.BRL, 2.00)
        );

        Assertions.assertNotNull(fiatOutputDataResponseEntity);
        Assertions.assertEquals(200, fiatOutputDataResponseEntity.getStatusCode().value());

    }

    @Test
    void shouldReturnFiatConvertedWhenReqValidBodyAndRateFromCache() throws WiseRateServiceException {

        final var fiatOutputDataResponseEntity = controller.post(
                new FiatInputData(Symbol.USD, Symbol.BRL, 2.00)
        );

        Assertions.assertNotNull(fiatOutputDataResponseEntity);
        Assertions.assertEquals(200, fiatOutputDataResponseEntity.getStatusCode().value());

    }


}
