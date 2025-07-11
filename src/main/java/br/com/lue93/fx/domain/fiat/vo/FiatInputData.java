package br.com.lue93.fx.domain.fiat.vo;

import br.com.lue93.fx.domain.fiat.entities.Symbol;

public record FiatInputData(Symbol from, Symbol to, Double amount) { }
