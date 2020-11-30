package hu.bme.mit.theta.cloud.cfa.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * CexFileResponse
 */



public class CexFileResponse   {
  @JsonProperty("cex")
  private byte[] cex = null;

  public CexFileResponse cex(byte[] cex) {
    this.cex = cex;
    return this;
  }

  /**
   * Counterexample file
   * @return cex
   **/
  @Schema(description = "Counterexample file")
    public byte[] getCex() {
    return cex;
  }

  public void setCex(byte[] cex) {
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
    CexFileResponse cexFileResponse = (CexFileResponse) o;
    return Objects.equals(this.cex, cexFileResponse.cex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cex);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CexFileResponse {\n");
    
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
