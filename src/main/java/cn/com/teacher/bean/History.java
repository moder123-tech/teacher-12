package cn.com.teacher.bean;

import java.io.Serializable;

public class History implements Serializable {

  private Integer h_id;
  private String h_forrign;
  private String h_path;
  private String h_content;

  public Integer getH_id() {
    return h_id;
  }

  public void setH_id(Integer h_id) {
    this.h_id = h_id;
  }

  public String getH_forrign() {
    return h_forrign;
  }

  public void setH_forrign(String h_forrign) {
    this.h_forrign = h_forrign;
  }

  public String getH_path() {
    return h_path;
  }

  public void setH_path(String h_path) {
    this.h_path = h_path;
  }

  public String getH_content() {
    return h_content;
  }

  public void setH_content(String h_content) {
    this.h_content = h_content;
  }

  @Override
  public String toString() {
    return "History{" +
            "h_id=" + h_id +
            ", h_forrign='" + h_forrign + '\'' +
            ", h_path='" + h_path + '\'' +
            ", h_content='" + h_content + '\'' +
            '}';
  }
}
