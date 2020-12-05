package hu.bme.mit.theta.cloud.rest.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

/**
 * StartStsProcessRequest
 */



public class StartStsProcessRequest   {
  @JsonProperty("notificationAddress")
  private String notificationAddress = null;

  /**
   * Gets or Sets configType
   */
  public enum ConfigTypeEnum {
    CFA("CFA"),
    
    STS("STS"),
    
    XTA("XTA"),
    
    XSTS("XSTS");

    private String value;

    ConfigTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ConfigTypeEnum fromValue(String text) {
      for (ConfigTypeEnum b : ConfigTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("configType")
  private ConfigTypeEnum configType = null;

  @JsonProperty("configs")
  
  private List<StsConfig> configs = new ArrayList<>();

  public StartStsProcessRequest notificationAddress(String notificationAddress) {
    this.notificationAddress = notificationAddress;
    return this;
  }

  /**
   * Get notificationAddress
   * @return notificationAddress
   **/
  @Schema(description = "")
    public String getNotificationAddress() {
    return notificationAddress;
  }

  public void setNotificationAddress(String notificationAddress) {
    this.notificationAddress = notificationAddress;
  }

  public StartStsProcessRequest configType(ConfigTypeEnum configType) {
    this.configType = configType;
    return this;
  }

  /**
   * Get configType
   * @return configType
   **/
  @Schema(required = true, description = "")
    public ConfigTypeEnum getConfigType() {
    return configType;
  }

  public void setConfigType(ConfigTypeEnum configType) {
    this.configType = configType;
  }

  public StartStsProcessRequest configs(List<StsConfig> configs) {
    this.configs = configs;
    return this;
  }

  public StartStsProcessRequest addConfigsItem(StsConfig configsItem) {
    this.configs.add(configsItem);
    return this;
  }

  /**
   * Get configs
   * @return configs
   **/
  @Schema(required = true, description = "")
    public List<StsConfig> getConfigs() {
    return configs;
  }

  public void setConfigs(List<StsConfig> configs) {
    this.configs = configs;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StartStsProcessRequest startStsProcessRequest = (StartStsProcessRequest) o;
    return Objects.equals(this.notificationAddress, startStsProcessRequest.notificationAddress) &&
        Objects.equals(this.configType, startStsProcessRequest.configType) &&
        Objects.equals(this.configs, startStsProcessRequest.configs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationAddress, configType, configs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StartStsProcessRequest {\n");
    
    sb.append("    notificationAddress: ").append(toIndentedString(notificationAddress)).append("\n");
    sb.append("    configType: ").append(toIndentedString(configType)).append("\n");
    sb.append("    configs: ").append(toIndentedString(configs)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
