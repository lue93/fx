package br.com.lue93.fx.domain.fiat.services;

import br.com.lue93.fx.domain.fiat.vo.FiatInputData;
import br.com.lue93.fx.domain.fiat.vo.FiatOutputData;
import br.com.lue93.fx.domain.rate.vo.RateInput;

public interface FiatExchangeService {

     FiatOutputData exchangeFiatWith(RateInput rate, FiatInputData it);

}
