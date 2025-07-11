package br.com.lue93.fx.unit.application.services.rate.mock;

import br.com.lue93.fx.application.services.fiat.FiatExchangeServiceWiseRates;
import br.com.lue93.fx.domain.rate.entities.Rate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static br.com.lue93.fx.domain.fiat.entities.Symbol.BRL;
import static br.com.lue93.fx.domain.fiat.entities.Symbol.USD;


public class WiseMockResponse {

    private static final List<Rate> rates = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(FiatExchangeServiceWiseRates.class);

    public static List<Rate> get200Response() {

        Rate rate1 = new Rate(USD, BRL, 4.9379, "1702749167489");
        Rate rate2 = new Rate(USD, BRL, 4.916, "1702598400000");

        rates.add(rate1);
        rates.add(rate2);

        LOGGER.debug("A lista de objetos de Rate devolvida pelo BUILDER foi: {}", rates);

        return rates;
    }

    public static List<Rate> get200EmptyResponse() {

        LOGGER.debug("A lista de objetos de Rate devolvida pelo BUILDER foi: {}", rates);

        return rates;
    }

}
