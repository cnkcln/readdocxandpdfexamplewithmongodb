package com.osi.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class EmpDetails {

  @NotNull
  private String name;
  private String dob;
  private String total_Experience;
  private String relevant_Experience;
  private String qualification;
  private String marks_Score_Percentage;

  public EmpDetails() {}

  public EmpDetails(String name, String DOB, String total_Experience, String relevant_Experience,
		  String qualification,String  marks_Score_Percentage) {
    this.name = name;
    this.dob = dob;
    this.total_Experience = total_Experience;
    this.relevant_Experience = relevant_Experience;
    this.marks_Score_Percentage = marks_Score_Percentage;
  }
}