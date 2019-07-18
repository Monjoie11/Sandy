package com.learning.sandwich.sandy.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity
public class Sandwich implements Serializable {

  private static final long serialVersionUID = 1L;

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "sandwich_id")
  @SerializedName("id")
  private long sandwichId;

  @ColumnInfo(name = "sandwich_style")
  @SerializedName("style")
  @Nullable
  private int sandwichStyle;

  @ColumnInfo(name = "machine_eat")
  @SerializedName("machineEat")
  private boolean machineEat;

  @ColumnInfo(name = "human_eat")
  @SerializedName("humanEat")
  private boolean humanEat;

  @NonNull
  @ColumnInfo(name = "file_name")
  @SerializedName("file")
  private String fileName;

  @SerializedName("isResource")
  @ColumnInfo(name = "image_resource")
  private boolean imageResource;

  public boolean isImageResource() {
    return imageResource;
  }

  public void setImageResource(boolean imageResource) {
    this.imageResource = imageResource;
  }

  public long getSandwichId() {
    return sandwichId;
  }

  public void setSandwichId(long sandwichId) {
    this.sandwichId = sandwichId;
  }

  public int getSandwichStyle() {
    return sandwichStyle;
  }

  public void setSandwichStyle(int sandwichStyle) {
    this.sandwichStyle = sandwichStyle;
  }

  public boolean isMachineEat() {
    return machineEat;
  }

  public void setMachineEat(boolean machineEat) {
    this.machineEat = machineEat;
  }

  public boolean isHumanEat() {
    return humanEat;
  }

  public void setHumanEat(boolean humanEat) {
    this.humanEat = humanEat;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


}
