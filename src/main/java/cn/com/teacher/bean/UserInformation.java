package cn.com.teacher.bean;


import java.io.Serializable;

public class UserInformation implements Serializable {

  private Integer u_id;
  private String u_number;
  private String u_password;
  private String u_head;
  private String u_name;

  public UserInformation(Integer u_id, String u_number, String u_password, String u_head, String u_name) {
    this.u_id = u_id;
    this.u_number = u_number;
    this.u_password = u_password;
    this.u_head = u_head;
    this.u_name = u_name;
  }
  public UserInformation(){

  }

  public Integer getU_id() {
    return u_id;
  }

  public void setU_id(Integer u_id) {
    this.u_id = u_id;
  }

  public String getU_number() {
    return u_number;
  }

  public void setU_number(String u_number) {
    this.u_number = u_number;
  }

  public String getU_password() {
    return u_password;
  }

  public void setU_password(String u_password) {
    this.u_password = u_password;
  }

  public String getU_head() {
    return u_head;
  }

  public void setU_head(String u_head) {
    this.u_head = u_head;
  }

  public String getU_name() {
    return u_name;
  }

  public void setU_name(String u_name) {
    this.u_name = u_name;
  }

  @Override
  public String toString() {
    return "UserInformation{" +
            "u_id=" + u_id +
            ", u_number='" + u_number + '\'' +
            ", u_password='" + u_password + '\'' +
            ", u_head='" + u_head + '\'' +
            ", u_name='" + u_name + '\'' +
            '}';
  }
}
