package hu.bme.mit.theta.cloud.rest.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

/**
 * StartXtaProcessRequest
 */



public class StartXtaProcessRequest   {
  @JsonProperty("notificationAddress")
  private String notificationAddress = null;

  @JsonProperty("configType")
  private String configType = null;

  @JsonProperty("configs")
  
  private List<XtaConfig> configs = new ArrayList<>();

  public StartXtaProcessRequest notificationAddress(String notificationAddress) {
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

  public StartXtaProcessRequest configType(String configType) {
    this.configType = configType;
    return this;
  }

  /**
   * Get configType
   * @return configType
   **/
  @Schema(required = true, description = "")
    public String getConfigType() {
    return configType;
  }

  public void setConfigType(String configType) {
    this.configType = configType;
  }

  public StartXtaProcessRequest configs(List<XtaConfig> configs) {
    this.configs = configs;
    return this;
  }

  public StartXtaProcessRequest addConfigsItem(XtaConfig configsItem) {
    this.configs.add(configsItem);
    return this;
  }

  /**
   * Get configs
   * @return configs
   **/
  @Schema(required = true, description = "")
    public List<XtaConfig> getConfigs() {
    return configs;
  }

  public void setConfigs(List<XtaConfig> configs) {
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
    StartXtaProcessRequest startXtaProcessRequest = (StartXtaProcessRequest) o;
    return Objects.equals(this.notificationAddress, startXtaProcessRequest.notificationAddress) &&
        Objects.equals(this.configType, startXtaProcessRequest.configType) &&
        Objects.equals(this.configs, startXtaProcessRequest.configs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationAddress, configType, configs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StartXtaProcessRequest {\n");
    
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
