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
@JsonPropertyOrder({"No action mapping", "Duplicates"})
public class DroppedEvents {
  @JsonProperty("No action mapping")
  private Long noActionMapping;
  @JsonProperty("Duplicates")
  private Long duplicates;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("No action mapping")
  public Long getNoActionMapping() {
    return noActionMapping;
  }

  @JsonProperty("No action mapping")
  public void setNoActionMapping(Long noActionMapping) {
    this.noActionMapping = noActionMapping;
  }

  @JsonProperty("Duplicates")
  public Long getDuplicates() {
    return duplicates;
  }

  @JsonProperty("Duplicates")
  public void setDuplicates(Long duplicates) {
    this.duplicates = duplicates;
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
    return new HashCodeBuilder().append(noActionMapping).append(duplicates)
        .append(additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof DroppedEvents) == false) {
      return false;
    }
    DroppedEvents rhs = ((DroppedEvents) other);
    return new EqualsBuilder().append(noActionMapping, rhs.noActionMapping)
        .append(duplicates, rhs.duplicates).append(additionalProperties, rhs.additionalProperties)
        .isEquals();
  }

}
