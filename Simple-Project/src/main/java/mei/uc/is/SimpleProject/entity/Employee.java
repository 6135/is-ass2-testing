package mei.uc.is.SimpleProject.entity;

import org.springframework.data.annotation.*;

import lombok.*;

@Data
@Builder
public class Employee {
  @Id
  private Long id;
  private String name;
}