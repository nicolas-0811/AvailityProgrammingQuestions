package com.backend.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "npi"),
      @UniqueConstraint(columnNames = "email")
    })
public class User {
  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usid_generator")
  @SequenceGenerator(name = "usid_generator", initialValue = 1, allocationSize = 1, sequenceName = "usid_seq")
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String firstName;

  @NotBlank
  @Size(max = 20)
  private String lastName;

  @NotBlank
  @Size(max = 20)
  private String npi;

  @NotBlank
  @Size(max = 40)
  private String address;

  @NotBlank
  @Size(max = 10)
  private String phone;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  public User() {
  }

  public User(String firstName, String lastName, String npi, String address, String phone, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.npi = npi;
    this.address = address;
    this.phone = phone;
    this.email = email;
  }
}