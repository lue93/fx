package br.com.lue93.fx.domain.fiat.entities;


public class FiatBuilder {
    private Fiat instance;

    private FiatBuilder() {
        instance = new Fiat();
    }

    public static FiatBuilder builder() {
        return new FiatBuilder();
    }

    public FiatBuilder from(String sym) {
        instance.setFrom(Symbol.valueOf(sym));
        return this;
    }

    public FiatBuilder amount(Double amount) {
        instance.setAmount(amount);
        return this;
    }

    public FiatBuilder to(String symbol) {
        instance.setTo(Symbol.valueOf(symbol));
        return this;
    }

    public Fiat build() {
        return instance;
    }
}
