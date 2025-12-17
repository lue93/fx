package br.com.lue93.fx.unit.application.services.fiat;

import br.com.lue93.fx.domain.fiat.entities.Fiat;
import br.com.lue93.fx.domain.fiat.entities.FiatBuilder;
import br.com.lue93.fx.domain.fiat.vo.FiatMapper;
import br.com.lue93.fx.domain.fiat.entities.Symbol;
import br.com.lue93.fx.domain.rate.entities.Rate;
import br.com.lue93.fx.domain.rate.entities.RateBuilder;
import br.com.lue93.fx.domain.rate.services.RateService;
import br.com.lue93.fx.application.services.rate.WiseRateServiceException;
import br.com.lue93.fx.domain.rate.vo.RateInput;
import br.com.lue93.fx.domain.rate.vo.RatesMapper;
import br.com.lue93.fx.application.services.fiat.FiatExchangeServiceWiseRates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
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

        Rate rate = RateBuilder.builder()
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
