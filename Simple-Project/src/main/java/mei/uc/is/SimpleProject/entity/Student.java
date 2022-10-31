package mei.uc.is.SimpleProject.entity;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.*;

@Data
@Builder
@Table("student")
public class Student {
  @Id
  private Long id;
  @Column("name")
  private String name;
  @Column("birthdate")
  private LocalDate birthdate;
  @Column("completed_credits")
  private int credits;
  @Column("gpa")
  private double gpa;

}