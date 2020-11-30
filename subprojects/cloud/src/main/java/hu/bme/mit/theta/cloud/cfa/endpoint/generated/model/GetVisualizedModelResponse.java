package hu.bme.mit.theta.cloud.cfa.endpoint.generated.model;

import java.io.File;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * GetVisualizedModelResponse
 */



public class GetVisualizedModelResponse   {
  @JsonProperty("cex")
  private File cex = null;

  public GetVisualizedModelResponse cex(File cex) {
    this.cex = cex;
    return this;
  }

  /**
   * Visualized model file
   * @return cex
   **/
  @Schema(description = "Visualized model file")
    public File getCex() {
    return cex;
  }

  public void setCex(File cex) {
    this.cex = cex;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetVisualizedModelResponse getVisualizedModelResponse = (GetVisualizedModelResponse) o;
    return Objects.equals(this.cex, getVisualizedModelResponse.cex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cex);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetVisualizedModelResponse {\n");
    
    sb.append("    cex: ").append(toIndentedString(cex)).append("\n");
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
