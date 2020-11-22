package hu.bme.mit.theta.cloud.cfa.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.io.Resource;

/**
 * GetModelFileResponse
 */



public class GetModelFileResponse   {
  @JsonProperty("fileName")
  private String fileName = null;

  @JsonProperty("content")
  private Resource content = null;

  public GetModelFileResponse fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * fileName of the newly uploaded CFA model
   * @return fileName
   **/
  @Schema(required = true, description = "fileName of the newly uploaded CFA model")
    public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public GetModelFileResponse content(Resource content) {
    this.content = content;
    return this;
  }

  /**
   * The content of the file
   * @return content
   **/
  @Schema(required = true, description = "The content of the file")
    public Resource getContent() {
    return content;
  }

  public void setContent(Resource content) {
    this.content = content;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetModelFileResponse getModelFileResponse = (GetModelFileResponse) o;
    return Objects.equals(this.fileName, getModelFileResponse.fileName) &&
        Objects.equals(this.content, getModelFileResponse.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetModelFileResponse {\n");
    
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
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
