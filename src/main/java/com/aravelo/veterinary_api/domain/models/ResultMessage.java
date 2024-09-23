package com.aravelo.veterinary_api.domain.models;

public class ResultMessage {

  private String infoMessage;
  private Integer rowChanged;

  public ResultMessage() {
  }

  public ResultMessage(String infoMessage, Integer rowChanged) {
    this.infoMessage = infoMessage;
    this.rowChanged = rowChanged;
  }

  public String getInfoMessage() {
    return infoMessage;
  }

  public void setInfoMessage(String infoMessage) {
    this.infoMessage = infoMessage;
  }

  public Integer getRowChanged() {
    return rowChanged;
  }

  public void setRowChanged(Integer rowChanged) {
    this.rowChanged = rowChanged;
  }

}
