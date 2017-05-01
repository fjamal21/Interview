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
@JsonPropertyOrder({"linesRead", "droppedEventsCounts", "droppedEvents", "uniqueUsers",
    "uniqueFiles", "startDate", "endDate", "actions"})
public class StatsFilter {
  @JsonProperty("linesRead")
  private Long linesRead;
  @JsonProperty("droppedEventsCounts")
  private Long droppedEventsCounts;
  @JsonProperty("droppedEvents")
  private DroppedEvents droppedEvents;
  @JsonProperty("uniqueUsers")
  private Long uniqueUsers;
  @JsonProperty("uniqueFiles")
  private Long uniqueFiles;
  @JsonProperty("startDate")
  private String startDate;
  @JsonProperty("endDate")
  private String endDate;
  @JsonProperty("actions")
  private Actions actions;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("linesRead")
  public Long getLinesRead() {
    return linesRead;
  }

  @JsonProperty("linesRead")
  public void setLinesRead(Long linesRead) {
    this.linesRead = linesRead;
  }

  @JsonProperty("droppedEventsCounts")
  public Long getDroppedEventsCounts() {
    return droppedEventsCounts;
  }

  @JsonProperty("droppedEventsCounts")
  public void setDroppedEventsCounts(Long droppedEventsCounts) {
    this.droppedEventsCounts = droppedEventsCounts;
  }

  @JsonProperty("droppedEvents")
  public DroppedEvents getDroppedEvents() {
    return droppedEvents;
  }

  @JsonProperty("droppedEvents")
  public void setDroppedEvents(DroppedEvents droppedEvents) {
    this.droppedEvents = droppedEvents;
  }

  @JsonProperty("uniqueUsers")
  public Long getUniqueUsers() {
    return uniqueUsers;
  }

  @JsonProperty("uniqueUsers")
  public void setUniqueUsers(Long uniqueUsers) {
    this.uniqueUsers = uniqueUsers;
  }

  @JsonProperty("uniqueFiles")
  public Long getUniqueFiles() {
    return uniqueFiles;
  }

  @JsonProperty("uniqueFiles")
  public void setUniqueFiles(Long uniqueFiles) {
    this.uniqueFiles = uniqueFiles;
  }

  @JsonProperty("startDate")
  public String getStartDate() {
    return startDate;
  }

  @JsonProperty("startDate")
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  @JsonProperty("endDate")
  public String getEndDate() {
    return endDate;
  }

  @JsonProperty("endDate")
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  @JsonProperty("actions")
  public Actions getActions() {
    return actions;
  }

  @JsonProperty("actions")
  public void setActions(Actions actions) {
    this.actions = actions;
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
    return new HashCodeBuilder().append(linesRead).append(droppedEventsCounts)
        .append(droppedEvents).append(uniqueUsers).append(uniqueFiles).append(startDate)
        .append(endDate).append(actions).append(additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof StatsFilter) == false) {
      return false;
    }
    StatsFilter rhs = ((StatsFilter) other);
    return new EqualsBuilder().append(linesRead, rhs.linesRead)
        .append(droppedEventsCounts, rhs.droppedEventsCounts)
        .append(droppedEvents, rhs.droppedEvents).append(uniqueUsers, rhs.uniqueUsers)
        .append(uniqueFiles, rhs.uniqueFiles).append(startDate, rhs.startDate)
        .append(endDate, rhs.endDate).append(actions, rhs.actions)
        .append(additionalProperties, rhs.additionalProperties).isEquals();
  }

}
