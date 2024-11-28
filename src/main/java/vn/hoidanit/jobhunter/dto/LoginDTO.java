package vn.hoidanit.jobhunter.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
  @NotBlank(message = "username can't blank")
  private String username;
  @NotBlank(message = "password can't blank")
  private String password;

  public LoginDTO() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "LoginDTO [username=" + username + ", password=" + password + "]";
  }

}
