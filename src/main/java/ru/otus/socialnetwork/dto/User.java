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

//  @Column(nullable = false, unique = true, length = 45)
  @NotBlank
  private String email;

//  @Column(nullable = false, length = 64)
  @NotBlank
  private String password;

//  @Column(name = "first_name", nullable = false, length = 20)
  @NotBlank
  private String firstName;

//  @Column(name = "last_name", nullable = false, length = 20)
  @NotBlank
  private String lastName;

  private int age;

  private Sex sex;

  private String hobby;

  private String city;

  private Set<User> friends;
}
