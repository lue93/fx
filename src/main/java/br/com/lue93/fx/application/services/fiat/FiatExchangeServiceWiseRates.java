package br.com.lue93.fx.application.services.fiat;

import br.com.lue93.fx.domain.fiat.services.FiatExchangeService;
import br.com.lue93.fx.domain.fiat.vo.FiatInputData;
import br.com.lue93.fx.domain.fiat.vo.FiatOutputData;
import br.com.lue93.fx.domain.rate.vo.RateInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FiatExchangeServiceWiseRates implements FiatExchangeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FiatExchangeServiceWiseRates.class);

    @Override
    public FiatOutputData exchangeFiatWith(
            RateInput rate, FiatInputData fiat) {

       Double changed =  fiat.amount() * rate.value();
       LOGGER.info("O resultado do cambio de fiat ${}, com valor de rate ${}  foi  ${} ", fiat.amount(), rate.value(), changed);

        return new FiatOutputData(changed);
    }
}

