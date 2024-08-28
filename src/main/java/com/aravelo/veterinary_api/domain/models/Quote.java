package com.aravelo.veterinary_api.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quotes")
public class Quote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((petName == null) ? 0 : petName.hashCode());
    result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + ((time == null) ? 0 : time.hashCode());
    result = prime * result + ((symptoms == null) ? 0 : symptoms.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Quote other = (Quote) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (petName == null) {
      if (other.petName != null)
        return false;
    } else if (!petName.equals(other.petName))
      return false;
    if (ownerName == null) {
      if (other.ownerName != null)
        return false;
    } else if (!ownerName.equals(other.ownerName))
      return false;
    if (date == null) {
      if (other.date != null)
        return false;
    } else if (!date.equals(other.date))
      return false;
    if (time == null) {
      if (other.time != null)
        return false;
    } else if (!time.equals(other.time))
      return false;
    if (symptoms == null) {
      if (other.symptoms != null)
        return false;
    } else if (!symptoms.equals(other.symptoms))
      return false;
    return true;
  }

}
