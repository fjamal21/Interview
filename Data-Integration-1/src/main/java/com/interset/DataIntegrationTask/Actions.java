package com.interset.DataIntegrationTask;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"ADD", "REMOVE", "ACCESSED"})
public class Actions {
  @JsonProperty("ADD")
  private Long aDD;
  @JsonProperty("REMOVE")
  private Long rEMOVE;
  @JsonProperty("ACCESSED")
  private Long aCCESSED;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("ADD")
  public Long getADD() {
    return aDD;
  }

  @JsonProperty("ADD")
  public void setADD(Long aDD) {
    this.aDD = aDD;
  }

  @JsonProperty("REMOVE")
  public Long getREMOVE() {
    return rEMOVE;
  }

  @JsonProperty("REMOVE")
  public void setREMOVE(Long rEMOVE) {
    this.rEMOVE = rEMOVE;
  }

  @JsonProperty("ACCESSED")
  public Long getACCESSED() {
    return aCCESSED;
  }

  @JsonProperty("ACCESSED")
  public void setACCESSED(Long aCCESSED) {
    this.aCCESSED = aCCESSED;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(aDD).append(rEMOVE).append(aCCESSED)
        .append(additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof Actions) == false) {
      return false;
    }
    Actions rhs = ((Actions) other);
    return new EqualsBuilder().append(aDD, rhs.aDD).append(rEMOVE, rhs.rEMOVE)
        .append(aCCESSED, rhs.aCCESSED).append(additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
