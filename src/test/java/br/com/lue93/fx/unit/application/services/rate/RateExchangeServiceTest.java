package br.com.lue93.fx.unit.application.services.rate;

import br.com.lue93.fx.domain.fiat.entities.Symbol;
import br.com.lue93.fx.domain.rate.entities.Rate;
import br.com.lue93.fx.domain.rate.entities.RateCache;
import br.com.lue93.fx.domain.rate.services.FeignWiseClient;
import br.com.lue93.fx.application.services.rate.WiseRateServiceException;
import br.com.lue93.fx.domain.rate.vo.RateInput;
import br.com.lue93.fx.domain.rate.vo.RateOutPut;
import br.com.lue93.fx.application.services.rate.ProxyRateService;
import br.com.lue93.fx.application.services.rate.WiseRateService;
import br.com.lue93.fx.unit.application.services.rate.mock.RateMock;
import br.com.lue93.fx.unit.application.services.rate.mock.WiseMockResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class RateExchangeServiceTest {

    @Mock
    private WiseRateService wiseRateService;

    @Mock
    private RateCache rateCache;

    @InjectMocks
    private ProxyRateService proxyRateService;

    @Mock
    private FeignWiseClient client;

    @Test
    void shouldReturnCurrentRatesFromWiseWhenGetWithValidPath() throws WiseRateServiceException {

        List<Rate> response = WiseMockResponse.get200Response();
        Rate rateMock = RateMock.getInstance();
        RateOutPut rateOutPut = new RateOutPut(
                rateMock.getSource(),
                rateMock.getTarget(),
                rateMock.getValue(),
                rateMock.getTime()
        );


        Mockito.when(client.getRates(
                "USD",
                "BRL",
                1,
                "daily",
                "day" )
        ).thenReturn(response);


        Mockito.when(wiseRateService.getRates(
                any(RateInput.class))
        ).thenReturn(rateOutPut);
        RateOutPut rates = proxyRateService.getRates(
                new RateInput(
                        Symbol.USD,
                        Symbol.BRL,
                        1,
                        String.valueOf(System.currentTimeMillis())
                )
        );



        Assertions.assertInstanceOf(RateOutPut.class, rates);
        Assertions.assertEquals("USD", rates.source().name());
        Assertions.assertEquals("BRL", rates.target().name());
        Assertions.assertEquals(4.9379, rates.value());
        Assertions.assertEquals("1702749167489", rates.time());

    }


    @Test
    public void shouldReturnRateFromWiseWhenCacheIsEmptyThenCacheIsUp() throws WiseRateServiceException {

        List<Rate> response = WiseMockResponse.get200Response();
        Rate rateMock = RateMock.getInstance();
        RateOutPut rateOutPut = new RateOutPut(
                rateMock.getSource(),
                rateMock.getTarget(),
                rateMock.getValue(),
                rateMock.getTime()
        );


        Mockito.when(client.getRates(
                "USD",
                "BRL",
                1,
                "daily",
                "day" )
        ).thenReturn(response);

        Mockito.when(wiseRateService.getRates(
                any(RateInput.class))
        ).thenReturn(rateOutPut);
        RateOutPut rates = proxyRateService.getRates(
                new RateInput(
                        Symbol.USD,
                        Symbol.BRL,
                        1,
                        String.valueOf(System.currentTimeMillis())
                )
        );



        Assertions.assertInstanceOf(RateOutPut.class, rates);
        Assertions.assertEquals("USD", rates.source().name());
        Assertions.assertEquals("BRL", rates.target().name());
        Assertions.assertEquals(4.9379, rates.value());
        Assertions.assertEquals("1702749167489", rates.time());

    }



}
