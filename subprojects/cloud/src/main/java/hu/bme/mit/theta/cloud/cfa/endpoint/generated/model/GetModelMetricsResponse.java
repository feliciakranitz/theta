package hu.bme.mit.theta.cloud.cfa.endpoint.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

/**
 * GetModelMetricsResponse
 */



public class GetModelMetricsResponse   {
  @JsonProperty("vars")
  private int vars;

  @JsonProperty("boolVars")
  private long boolVars;

  @JsonProperty("intVars")
  private long intVars;

  @JsonProperty("bitvectorVars")
  private long bitvectorVars;

  @JsonProperty("arrayVars")
  private long arrayVars;

  @JsonProperty("locs")
  private int locs;

  @JsonProperty("edges")
  private int edges;

  @JsonProperty("assignments")
  private long assignments;

  @JsonProperty("assumptions")
  private long assumptions;

  @JsonProperty("havocs")
  private long havocs;

  @JsonProperty("cyclomaticComplexity")
  private int cyclomaticComplexity;

  public GetModelMetricsResponse vars(int vars) {
    this.vars = vars;
    return this;
  }

  /**
   * Get vars
   * @return vars
   **/
  @Schema(description = "")
    public int getVars() {
    return vars;
  }

  public void setVars(int vars) {
    this.vars = vars;
  }

  public GetModelMetricsResponse boolVars(long boolVars) {
    this.boolVars = boolVars;
    return this;
  }

  /**
   * Get boolVars
   * @return boolVars
   **/
  @Schema(description = "")
    public long getBoolVars() {
    return boolVars;
  }

  public void setBoolVars(long boolVars) {
    this.boolVars = boolVars;
  }

  public GetModelMetricsResponse intVars(long intVars) {
    this.intVars = intVars;
    return this;
  }

  /**
   * Get intVars
   * @return intVars
   **/
  @Schema(description = "")
    public long getIntVars() {
    return intVars;
  }

  public void setIntVars(long intVars) {
    this.intVars = intVars;
  }

  public GetModelMetricsResponse bitvectorVars(long bitvectorVars) {
    this.bitvectorVars = bitvectorVars;
    return this;
  }

  /**
   * Get bitvectorVars
   * @return bitvectorVars
   **/
  @Schema(description = "")
    public long getBitvectorVars() {
    return bitvectorVars;
  }

  public void setBitvectorVars(long bitvectorVars) {
    this.bitvectorVars = bitvectorVars;
  }

  public GetModelMetricsResponse arrayVars(long arrayVars) {
    this.arrayVars = arrayVars;
    return this;
  }

  /**
   * Get arrayVars
   * @return arrayVars
   **/
  @Schema(description = "")
    public long getArrayVars() {
    return arrayVars;
  }

  public void setArrayVars(long arrayVars) {
    this.arrayVars = arrayVars;
  }

  public GetModelMetricsResponse locs(int locs) {
    this.locs = locs;
    return this;
  }

  /**
   * Get locs
   * @return locs
   **/
  @Schema(description = "")
    public int getLocs() {
    return locs;
  }

  public void setLocs(int locs) {
    this.locs = locs;
  }

  public GetModelMetricsResponse edges(int edges) {
    this.edges = edges;
    return this;
  }

  /**
   * Get edges
   * @return edges
   **/
  @Schema(description = "")
    public int getEdges() {
    return edges;
  }

  public void setEdges(int edges) {
    this.edges = edges;
  }

  public GetModelMetricsResponse assignments(long assignments) {
    this.assignments = assignments;
    return this;
  }

  /**
   * Get assignments
   * @return assignments
   **/
  @Schema(description = "")
    public long getAssignments() {
    return assignments;
  }

  public void setAssignments(long assignments) {
    this.assignments = assignments;
  }

  public GetModelMetricsResponse assumptions(long assumptions) {
    this.assumptions = assumptions;
    return this;
  }

  /**
   * Get assumptions
   * @return assumptions
   **/
  @Schema(description = "")
    public long getAssumptions() {
    return assumptions;
  }

  public void setAssumptions(long assumptions) {
    this.assumptions = assumptions;
  }

  public GetModelMetricsResponse havocs(long havocs) {
    this.havocs = havocs;
    return this;
  }

  /**
   * Get havocs
   * @return havocs
   **/
  @Schema(description = "")
    public long getHavocs() {
    return havocs;
  }

  public void setHavocs(long havocs) {
    this.havocs = havocs;
  }

  public GetModelMetricsResponse cyclomaticComplexity(int cyclomaticComplexity) {
    this.cyclomaticComplexity = cyclomaticComplexity;
    return this;
  }

  /**
   * Get cyclomaticComplexity
   * @return cyclomaticComplexity
   **/
  @Schema(description = "")
    public int getCyclomaticComplexity() {
    return cyclomaticComplexity;
  }

  public void setCyclomaticComplexity(int cyclomaticComplexity) {
    this.cyclomaticComplexity = cyclomaticComplexity;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetModelMetricsResponse getModelMetricsResponse = (GetModelMetricsResponse) o;
    return Objects.equals(this.vars, getModelMetricsResponse.vars) &&
        Objects.equals(this.boolVars, getModelMetricsResponse.boolVars) &&
        Objects.equals(this.intVars, getModelMetricsResponse.intVars) &&
        Objects.equals(this.bitvectorVars, getModelMetricsResponse.bitvectorVars) &&
        Objects.equals(this.arrayVars, getModelMetricsResponse.arrayVars) &&
        Objects.equals(this.locs, getModelMetricsResponse.locs) &&
        Objects.equals(this.edges, getModelMetricsResponse.edges) &&
        Objects.equals(this.assignments, getModelMetricsResponse.assignments) &&
        Objects.equals(this.assumptions, getModelMetricsResponse.assumptions) &&
        Objects.equals(this.havocs, getModelMetricsResponse.havocs) &&
        Objects.equals(this.cyclomaticComplexity, getModelMetricsResponse.cyclomaticComplexity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vars, boolVars, intVars, bitvectorVars, arrayVars, locs, edges, assignments, assumptions, havocs, cyclomaticComplexity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetModelMetricsResponse {\n");
    
    sb.append("    vars: ").append(toIndentedString(vars)).append("\n");
    sb.append("    boolVars: ").append(toIndentedString(boolVars)).append("\n");
    sb.append("    intVars: ").append(toIndentedString(intVars)).append("\n");
    sb.append("    bitvectorVars: ").append(toIndentedString(bitvectorVars)).append("\n");
    sb.append("    arrayVars: ").append(toIndentedString(arrayVars)).append("\n");
    sb.append("    locs: ").append(toIndentedString(locs)).append("\n");
    sb.append("    edges: ").append(toIndentedString(edges)).append("\n");
    sb.append("    assignments: ").append(toIndentedString(assignments)).append("\n");
    sb.append("    assumptions: ").append(toIndentedString(assumptions)).append("\n");
    sb.append("    havocs: ").append(toIndentedString(havocs)).append("\n");
    sb.append("    cyclomaticComplexity: ").append(toIndentedString(cyclomaticComplexity)).append("\n");
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
