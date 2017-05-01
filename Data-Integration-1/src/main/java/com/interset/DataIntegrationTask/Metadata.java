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
@JsonPropertyOrder({"eventId", "timestamp", "ipAddr", "user", "file", "activity", "timeOffset"})
public class Metadata {
  @JsonProperty("eventId")
  private Long eventId;
  @JsonProperty("timestamp")
  private String timestamp;
  @JsonProperty("ipAddr")
  private String ipAddr;
  @JsonProperty("user")
  private String user;
  @JsonProperty("file")
  private String file;
  @JsonProperty("activity")
  private String activity;
  @JsonProperty("timeOffset")
  private String timeOffset;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("eventId")
  public Long getEventId() {
    return eventId;
  }

  @JsonProperty("eventId")
  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  @JsonProperty("timestamp")
  public String getTimestamp() {
    return timestamp;
  }

  @JsonProperty("timestamp")
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  @JsonProperty("ipAddr")
  public String getIpAddr() {
    return ipAddr;
  }

  @JsonProperty("ipAddr")
  public void setIpAddr(String ipAddr) {
    this.ipAddr = ipAddr;
  }

  @JsonProperty("user")
  public String getUser() {
    return user;
  }

  @JsonProperty("user")
  public void setUser(String user) {
    this.user = user;
  }

  @JsonProperty("file")
  public String getFile() {
    return file;
  }

  @JsonProperty("file")
  public void setFile(String file) {
    this.file = file;
  }

  @JsonProperty("activity")
  public String getActivity() {
    return activity;
  }

  @JsonProperty("activity")
  public void setActivity(String activity) {
    this.activity = activity;
  }

  @JsonProperty("timeOffset")
  public String getTimeOffset() {
    return timeOffset;
  }

  @JsonProperty("timeOffset")
  public void setTimeOffset(String timeOffset) {
    this.timeOffset = timeOffset;
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
    return new HashCodeBuilder().append(eventId).append(timestamp).append(ipAddr).append(user)
        .append(file).append(activity).append(timeOffset).append(additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof Metadata) == false) {
      return false;
    }
    Metadata rhs = ((Metadata) other);
    return new EqualsBuilder().append(eventId, rhs.eventId).append(timestamp, rhs.timestamp)
        .append(ipAddr, rhs.ipAddr).append(user, rhs.user).append(file, rhs.file)
        .append(activity, rhs.activity).append(timeOffset, rhs.timeOffset)
        .append(additionalProperties, rhs.additionalProperties).isEquals();
  }
}
