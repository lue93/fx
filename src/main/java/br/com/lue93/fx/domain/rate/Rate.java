package br.com.lue93.fx.domain.rate;

import br.com.lue93.fx.domain.fiat.Symbol;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
public class Rate {
    @JsonProperty("source")
    private Symbol source;
    @JsonProperty("target")
    private Symbol target;
    @JsonProperty("value")
    private double value;
    @JsonProperty("time")
    private String time;

    public Rate() {
    }
    public Rate(Symbol source, Symbol target, double value, String time) {
        this.source = source;
        this.target = target;
        this.value = value;
        this.time = time;
    }


}
