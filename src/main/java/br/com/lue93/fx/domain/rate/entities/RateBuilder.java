package br.com.lue93.fx.domain.rate.entities;

import br.com.lue93.fx.domain.fiat.entities.Symbol;

public class RateBuilder {

    private Rate instance;

    private RateBuilder() { instance = new Rate();}

    public static RateBuilder builder() { return new RateBuilder();}

    public RateBuilder source(Symbol source) {
        instance.setSource(source);
        return this;
    }

    public RateBuilder target(Symbol target) {
        instance.setTarget(target);
        return this;
    }

    public RateBuilder value(double value) {
        instance.setValue(value);
        return this;
    }

    public RateBuilder time(String time) {
        instance.setTime(time);
        return this;
    }

    public Rate build() {
        return instance;
    }

}
