package br.com.lue93.fx.domain.rate.entities;

import br.com.lue93.fx.domain.fiat.entities.Symbol;
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


    public Symbol getSource() {
        return source;
    }

    public Symbol getTarget() {
        return target;
    }

    public double getValue() {
        return value;
    }

    public String getTime() {
        return time;
    }

    public void setSource(Symbol source) {
        this.source = source;
    }

    public void setTarget(Symbol target) {
        this.target = target;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
