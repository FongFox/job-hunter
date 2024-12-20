package vn.hoidanit.jobhunter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResLoginDTO {
  private String accessToken;
  private UserLogin userLogin;

  @Getter
  @Setter
  public static class UserLogin {
    private int id;
    private String email;
    private String name;

    public UserLogin() {
    }

    public UserLogin(int id, String email, String name) {
      this.id = id;
      this.email = email;
      this.name = name;
    }
  }

}
