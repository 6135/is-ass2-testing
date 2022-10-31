package mei.uc.is.SimpleProject.handler;

import java.time.LocalDate;
import java.util.Date;

import lombok.*;

@Data
public class CreateStudent {

  private Long id;
  private String name;
  private LocalDate birthdate;
  private int credits;
  private double gpa;

}