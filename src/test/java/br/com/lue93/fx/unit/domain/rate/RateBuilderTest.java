package br.com.lue93.fx.unit.domain.rate;

import br.com.lue93.fx.domain.fiat.entities.Symbol;
import br.com.lue93.fx.domain.rate.entities.Rate;
import br.com.lue93.fx.domain.rate.entities.RateBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RateBuilderTest {

    @Test
    void deveRetornarUmObjetoRate() {
        Rate rate = RateBuilder.builder()
                .source(Symbol.USD)
                .target(Symbol.BRL)
                .value(4.9119)
                .time("1701993600000")
                .build();

        Assertions.assertEquals(Symbol.USD, rate.getSource());
        Assertions.assertEquals(Symbol.BRL, rate.getTarget());
        Assertions.assertEquals(4.9119, rate.getValue());
        Assertions.assertEquals("1701993600000", rate.getTime());
    }

}
