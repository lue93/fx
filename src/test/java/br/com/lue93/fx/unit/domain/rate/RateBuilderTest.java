package br.com.lue93.fx.unit.domain.rate;

import br.com.lue93.fx.domain.fiat.Symbol;
import br.com.lue93.fx.domain.rate.Rate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RateBuilderTest {

    @Test
    void deveRetornarUmObjetoRate() {
        Rate rate = Rate.builder()
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
