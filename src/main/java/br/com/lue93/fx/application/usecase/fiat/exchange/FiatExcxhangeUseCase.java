package br.com.lue93.fx.application.usecase.fiat.exchange;

import br.com.lue93.fx.domain.fiat.services.FiatExchangeService;
import br.com.lue93.fx.domain.fiat.vo.FiatInputData;
import br.com.lue93.fx.domain.fiat.vo.FiatOutputData;
import br.com.lue93.fx.domain.rate.entities.Rate;
import br.com.lue93.fx.application.services.rate.WiseRateServiceException;
import br.com.lue93.fx.domain.rate.vo.RateInput;
import br.com.lue93.fx.domain.rate.vo.RateOutPut;
import br.com.lue93.fx.domain.rate.vo.RatesMapper;
import br.com.lue93.fx.application.services.fiat.FiatExchangeServiceWiseRates;
import br.com.lue93.fx.application.services.rate.ProxyRateService;
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
