package br.com.lue93.fx.usecase.fiat.exchange;

import br.com.lue93.fx.domain.fiat.FiatExchangeService;
import br.com.lue93.fx.domain.fiat.FiatInputData;
import br.com.lue93.fx.domain.fiat.FiatOutputData;
import br.com.lue93.fx.domain.rate.*;
import br.com.ungaratto93.fx.domain.rate.*;
import br.com.lue93.fx.infra.fiat.FiatExchangeServiceWiseRates;
import br.com.lue93.fx.infra.rate.ProxyRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FiatExcxhangeUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(FiatExchangeServiceWiseRates.class);

    @Autowired
    private final FiatExchangeService fiatEchangeService;

    @Autowired
    private final ProxyRateService rateService;

    public FiatExcxhangeUseCase(ProxyRateService proxyRateService, FiatExchangeService fiatExchangeService) {
        this.rateService = proxyRateService;
        this.fiatEchangeService = fiatExchangeService;
    }

    public FiatOutputData exec(FiatInputData fiatInputData) throws WiseRateServiceException {
        LOGGER.info("Obtendo a taxa para conversao de fiat");
        RateOutPut rateOutPutFromWiseService = this.rateService.getRates(
                new RateInput(
                        fiatInputData.from(),
                        fiatInputData.to(),
                        fiatInputData.amount(),
                        String.valueOf(System.currentTimeMillis())
                )
        );

        LOGGER.info("Realizando a conversao com base na taxa fornecida");
        return this.fiatEchangeService.exchangeFiatWith(
                new RatesMapper().mapToRateInputDataFrom(
                        new Rate(rateOutPutFromWiseService.source(),
                                rateOutPutFromWiseService.target(),
                                rateOutPutFromWiseService.value(),
                                rateOutPutFromWiseService.time()
                        )
                ), fiatInputData
        );

    }
}
