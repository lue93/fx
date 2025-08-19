package br.com.lue93.fx.domain.fiat.vo;


import br.com.lue93.fx.domain.fiat.entities.Fiat;
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
