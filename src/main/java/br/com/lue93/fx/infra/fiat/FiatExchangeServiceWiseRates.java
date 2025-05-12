package br.com.lue93.fx.infra.fiat;

import br.com.lue93.fx.domain.fiat.FiatExchangeService;
import br.com.lue93.fx.domain.fiat.FiatInputData;
import br.com.lue93.fx.domain.fiat.FiatOutputData;
import br.com.lue93.fx.domain.rate.RateInput;
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

