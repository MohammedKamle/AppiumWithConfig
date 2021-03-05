package base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class DeviceConfigurator {
@JsonProperty("platform")
    private String platformName;
@JsonProperty("os-version")
    private String os;
@JsonProperty("device")
    private String device;
@JsonProperty("app")
    private String app;

}
