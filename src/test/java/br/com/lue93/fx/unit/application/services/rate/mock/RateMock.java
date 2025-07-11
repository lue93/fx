package br.com.lue93.fx.unit.application.services.rate.mock;

import br.com.lue93.fx.domain.fiat.entities.Symbol;
import br.com.lue93.fx.domain.rate.entities.Rate;
import br.com.lue93.fx.domain.rate.entities.RateBuilder;

public class RateMock {

    public static Rate getInstance() {
        return RateBuilder.builder()
                .source(Symbol.USD)
                .target(Symbol.BRL)
                .value(4.9379)
                .time("1702749167489")
                .build();
    }
}
