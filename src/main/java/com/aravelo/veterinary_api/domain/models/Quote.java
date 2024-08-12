package com.aravelo.veterinary_api.domain.models;

public class Quote {
  String id;
  String petName;
  String ownerName;
  String date;
  String time;
  String symptoms;

  public Quote(String petName, String ownerName, String date, String time, String symptoms) {
    this.petName = petName;
    this.ownerName = ownerName;
    this.date = date;
    this.time = time;
    this.symptoms = symptoms;
  }

  public Quote() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPetName() {
    return petName;
  }

  public void setPetName(String petName) {
    this.petName = petName;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getSymptoms() {
    return symptoms;
  }

  public void setSymptoms(String symptoms) {
    this.symptoms = symptoms;
  }

}
