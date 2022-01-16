package ru.otus.socialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
public class User {

//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @Column
  @NotBlank
  private String email;

//  @Column
  @NotBlank
  private String password;

//  @Column
  @NotBlank
  private String firstName;

//  @Column
  @NotBlank
  private String lastName;

  private int age;

  private Sex sex;

  private String hobby;

  private String city;

  private Set<User> friends;
}
