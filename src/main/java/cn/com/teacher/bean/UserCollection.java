package cn.com.teacher.bean;


import java.io.Serializable;

public class UserCollection implements Serializable {

  private Integer c_id;
  private String c_path;
  private String c_foreign;
  private String c_content;

  public Integer getC_id() {
    return c_id;
  }

  public void setC_id(Integer c_id) {
    this.c_id = c_id;
  }

  public String getC_path() {
    return c_path;
  }

  public void setC_path(String c_path) {
    this.c_path = c_path;
  }

  public String getC_foreign() {
    return c_foreign;
  }

  public void setC_foreign(String c_foreign) {
    this.c_foreign = c_foreign;
  }

  public String getC_content() {
    return c_content;
  }

  public void setC_content(String c_content) {
    this.c_content = c_content;
  }

  @Override
  public String toString() {
    return "UserCollection{" +
            "c_id=" + c_id +
            ", c_path='" + c_path + '\'' +
            ", c_foreign='" + c_foreign + '\'' +
            ", c_content='" + c_content + '\'' +
            '}';
  }
}
