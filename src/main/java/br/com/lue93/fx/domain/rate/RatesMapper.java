package br.com.lue93.fx.domain.rate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RatesMapper {
    public RateOutPut mapToRateOutPutFrom(Rate rate) {
        return new RateOutPut(
                rate.getSource(),
                rate.getTarget(),
                rate.getValue(),
                rate.getTime()
        );
    }

    public RateInput mapToRateInputDataFrom(Rate rate) {
        return new RateInput(
                rate.getSource(),
                rate.getTarget(),
                rate.getValue(),
                rate.getTime()
        );
    }
}
