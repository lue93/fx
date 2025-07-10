package br.com.lue93.fx.integrate.ui;

import br.com.lue93.fx.domain.fiat.FiatInputData;
import br.com.lue93.fx.domain.fiat.Symbol;
import br.com.lue93.fx.domain.rate.WiseRateServiceException;
import br.com.ungaratto93.fx.domain.rate.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.lue93.fx.adapters.FiatExchangeController;


@SpringBootTest
@RunWith(SpringRunner.class)
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
