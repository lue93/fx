package br.com.lue93.fx.domain.fiat.entities;

import lombok.*;

@Getter
@Setter
public class Fiat {

    private Symbol from;
    private Double amount;
    private Symbol to;

    public Fiat(Symbol from, Double amount, Symbol to) {
        this.from = from;
        this.amount = amount;
        this.to = to;
    }

    public Fiat() {

    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setFrom(Symbol from) {
        this.from = from;
    }

    public Symbol getFrom() {
        return from;
    }

    public void setTo(Symbol to) {
        this.to = to;
    }

    public Symbol getTo() {
        return to;
    }
}
