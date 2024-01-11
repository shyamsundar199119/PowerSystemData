package eu.statnett.powersystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PowerRecord {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("Minutes1UTC")
    private String minutes1UTC;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("Minutes1DK")
    private String minutes1DK;

    @JsonProperty("OnshoreWindPower")
    private double onshoreWindPower;

    // Add other fields as needed


    public String getMinutes1UTC() {
        return minutes1UTC;
    }

    public void setMinutes1UTC(String minutes1UTC) {
        this.minutes1UTC = minutes1UTC;
    }

    public String getMinutes1DK() {
        return minutes1DK;
    }

    public void setMinutes1DK(String minutes1DK) {
        this.minutes1DK = minutes1DK;
    }

    public double getOnshoreWindPower() {
        return onshoreWindPower;
    }

    public void setOnshoreWindPower(double onshoreWindPower) {
        this.onshoreWindPower = onshoreWindPower;
    }
}
