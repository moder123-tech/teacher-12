package cn.com.teacher.bean;


import java.io.Serializable;

public class Resources implements Serializable {

  private Integer r_id;
  private String r_path;
  private String r_content;
  private String r_label;

  public Resources() {
  }

  public Integer getR_id() {
    return r_id;
  }

  public void setR_id(Integer r_id) {
    this.r_id = r_id;
  }

  public String getR_path() {
    return r_path;
  }

  public void setR_path(String r_path) {
    this.r_path = r_path;
  }

  public String getR_content() {
    return r_content;
  }

  public void setR_content(String r_content) {
    this.r_content = r_content;
  }

  public String getR_label() {
    return r_label;
  }

  public void setR_label(String r_label) {
    this.r_label = r_label;
  }

  @Override
  public String toString() {
    return "Resources{" +
            "r_id=" + r_id +
            ", r_path='" + r_path + '\'' +
            ", r_content='" + r_content + '\'' +
            ", r_label='" + r_label + '\'' +
            '}';
  }
}
