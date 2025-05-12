package br.com.lue93.fx.unit.domain.fiat;

import br.com.lue93.fx.domain.fiat.Fiat;
import br.com.lue93.fx.domain.fiat.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FiatTest {

    @Test
    void mustReturnFiatInstance() {
        Double amount = 10.00;
        Fiat fiat = new Fiat(Symbol.BRL, amount, Symbol.USD);

        Assertions.assertInstanceOf(Fiat.class, fiat);
        Assertions.assertEquals(Symbol.BRL, fiat.getFrom());
        Assertions.assertEquals(amount, fiat.getAmount());
        Assertions.assertEquals(Symbol.USD, fiat.getTo());
    }

}
