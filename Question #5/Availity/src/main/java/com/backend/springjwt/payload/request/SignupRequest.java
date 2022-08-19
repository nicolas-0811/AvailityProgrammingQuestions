package com.backend.springjwt.payload.request;

import javax.validation.constraints.*;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String firstName;

  @NotBlank
  @Size(min = 3, max = 20)
  private String lastName;

  @NotBlank
  @Size(min = 5, max = 20)
  private String npi;

  @NotBlank
  @Size(min = 5, max = 40)
  private String address;

  @NotBlank
  @Size(min = 1, max = 10)
  private String phone;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  public String getFirstName() {
      return firstName;
      }

  public void setFirstName(String firstName) {
      this.firstName = firstName;
      }

  public String getLastName() {
      return lastName;
    }

  public void setLastName(String lastName) {
      this.lastName = lastName;
      }

  public String getNpi() {
      return npi;
      }

  public void setNpi(String npi) {
      this.npi = npi;
      }

  public String getAddress() {
      return address;
      }

  public void setAddress(String address) {
      this.address = address;
      }

  public String getPhone() {
      return phone;
      }

  public void setPhone(String phone) {
      this.phone = phone;
      }

  public String getEmail() {
      return email;
      }

  public void setEmail(String email) {
      this.email = email;
      }
}