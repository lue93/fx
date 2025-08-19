package br.com.lue93.fx.unit.application.services.rate;

import br.com.lue93.fx.domain.fiat.entities.Symbol;
import br.com.lue93.fx.domain.rate.entities.Rate;
import br.com.lue93.fx.domain.rate.services.FeignWiseClient;
import br.com.lue93.fx.application.services.rate.WiseRateServiceException;
import br.com.lue93.fx.domain.rate.vo.RateInput;
import br.com.lue93.fx.domain.rate.vo.RateOutPut;
import br.com.lue93.fx.application.services.rate.WiseRateService;
import br.com.lue93.fx.unit.application.services.rate.mock.WiseMockResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WiseRateTest {

    @InjectMocks
    private WiseRateService wiseRateService;

    @Mock
    private FeignWiseClient client;

    @Test
    public void shouldThrowExcWhenRateFromWiseIsEmpty() {

        List<Rate> response = WiseMockResponse.get200EmptyResponse();

        RateInput rateInput = new RateInput(
                Symbol.USD,
                Symbol.BRL,
                1,
                String.valueOf(System.currentTimeMillis())
        );

        Mockito.when(client.getRates(
                rateInput.source().name(),
                rateInput.target().name(),
                1,
                "daily",
                "day")
        ).thenReturn(response);

        try {
            RateOutPut rates = wiseRateService.getRates(
                    rateInput
            );
        } catch (WiseRateServiceException e) {
            Assertions.assertEquals(WiseRateServiceException.class , e.getClass());
        }

    }


}
