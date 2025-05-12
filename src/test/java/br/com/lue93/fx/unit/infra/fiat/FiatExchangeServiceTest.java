package br.com.lue93.fx.unit.infra.fiat;

import br.com.lue93.fx.domain.fiat.Fiat;
import br.com.lue93.fx.domain.fiat.FiatBuilder;
import br.com.lue93.fx.domain.fiat.FiatMapper;
import br.com.lue93.fx.domain.fiat.Symbol;
import br.com.lue93.fx.domain.rate.*;
import br.com.lue93.fx.infra.fiat.FiatExchangeServiceWiseRates;
import br.com.ungaratto93.fx.domain.rate.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
class FiatExchangeServiceTest {

    @Autowired
    FiatExchangeServiceWiseRates fiatExchangeService;

    @Autowired
    RatesMapper ratesMapper;

    @Autowired
    FiatMapper fiatMapper;

    @Mock
    RateService rateService;

    @Test
    void deveRetornarTrocaDeValorFiat() throws WiseRateServiceException {

        Rate rate = Rate.builder()
                .source(Symbol.USD)
                .target(Symbol.BRL)
                .value(4.9119)
                .time("1701993600000")
               .build();

        Fiat fiat = FiatBuilder.builder()
                .from("USD")
                .amount(2.00)
                .to("BRL")
                .build();

        Mockito.when(
                rateService.getRates(any(RateInput.class)))
                .thenReturn(
                        ratesMapper.mapToRateOutPutFrom(rate)
                );

        var out = fiatExchangeService.exchangeFiatWith(
                ratesMapper.mapToRateInputDataFrom(rate),
                fiatMapper.mapToFiatInputData(fiat)
        );

        Assertions.assertNotNull(out);
        Assertions.assertEquals(9.8238, out.fiat());

    }

}
