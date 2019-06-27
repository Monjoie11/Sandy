package com.learning.sandwich.sandy.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity
public class Response implements Serializable {

  private static final long serialVersionUID = 1L;

  @ColumnInfo(name = "response_id")
  @PrimaryKey
  private long responseId;

  @ColumnInfo(name = "response_category")
  private int responseCategory;

  @ColumnInfo(name = "sandy_speaks")
  private String sandySpeaks;

  public long getResponseId() {
    return responseId;
  }

  public void setResponseId(long responseId) {
    this.responseId = responseId;
  }

  public int getResponseCategory() {
    return responseCategory;
  }

  public void setResponseCategory(int responseCategory) {
    this.responseCategory = responseCategory;
  }

  public String getSandySpeaks() {
    return sandySpeaks;
  }

  public void setSandySpeaks(String sandySpeaks) {
    this.sandySpeaks = sandySpeaks;
  }



}
