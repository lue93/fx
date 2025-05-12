package br.com.lue93.fx.domain.fiat;

import br.com.lue93.fx.domain.rate.RateInput;

public interface FiatExchangeService {

     FiatOutputData exchangeFiatWith(RateInput rate, FiatInputData it);

}
