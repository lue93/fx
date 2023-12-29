package br.com.ungaratto93.fx.domain.fiat;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FiatMapper {
    public FiatInputData mapToFiatInputData(Fiat fiat) {
        return new FiatInputData(
                fiat.getFrom(),
                fiat.getTo(),
                fiat.getAmount()
        );
    }
}
