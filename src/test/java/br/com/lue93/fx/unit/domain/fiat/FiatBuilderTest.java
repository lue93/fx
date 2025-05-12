package br.com.lue93.fx.unit.domain.fiat;

import br.com.lue93.fx.domain.fiat.Fiat;
import br.com.lue93.fx.domain.fiat.FiatBuilder;
import br.com.lue93.fx.domain.fiat.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FiatBuilderTest {

    @Test
    void deveRetornarUmObjetoFiat() {
        Fiat fiat = FiatBuilder.builder()
                .from("USD")
                .amount(1.00)
                .to("BRL")
                .build();

        Assertions.assertEquals(Symbol.USD, fiat.getFrom());
        Assertions.assertEquals(1.00, fiat.getAmount());
        Assertions.assertEquals(Symbol.BRL, fiat.getTo());

    }

}
