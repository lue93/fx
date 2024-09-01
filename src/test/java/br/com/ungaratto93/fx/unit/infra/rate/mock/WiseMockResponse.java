package br.com.ungaratto93.fx.unit.infra.rate.mock;

import br.com.ungaratto93.fx.infra.fiat.FiatExchangeServiceWiseRates;
import br.com.ungaratto93.fx.domain.rate.Rate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static br.com.ungaratto93.fx.domain.fiat.Symbol.BRL;
import static br.com.ungaratto93.fx.domain.fiat.Symbol.USD;


public class WiseMockResponse {

    private static final List<Rate> rates = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(FiatExchangeServiceWiseRates.class);

    public static List<Rate> get200Response() {

        Rate rate1 = new Rate(USD, BRL, 4.9379, "1702749167489");
        Rate rate2 = new Rate(USD, BRL, 4.916, "1702598400000");

        rates.add(rate1);
        rates.add(rate2);

        LOGGER.info("A lista de objetos de Rate devolvida pelo BUILDER foi: {}", rates);

        return rates;
    }

    public static List<Rate> get200EmptyResponse() {

        LOGGER.info("A lista de objetos de Rate devolvida pelo BUILDER foi: {}", rates);

        return rates;
    }

}
