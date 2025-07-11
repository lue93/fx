package br.com.lue93.fx.domain.rate.vo;

import br.com.lue93.fx.domain.fiat.entities.Symbol;

public record RateOutPut(
        Symbol source, Symbol target, double value, String time
) { }
