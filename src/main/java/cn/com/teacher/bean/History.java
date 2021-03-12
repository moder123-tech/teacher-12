package cn.com.teacher.bean;

public class History {

  private long hid;
  private String hForeign;
  private String hPath;
  private String hContent;


  public long getHid() {
    return hid;
  }

  public void setHid(long hid) {
    this.hid = hid;
  }


  public String getHForeign() {
    return hForeign;
  }

  public void setHForeign(String hForeign) {
    this.hForeign = hForeign;
  }


  public String getHPath() {
    return hPath;
  }

  public void setHPath(String hPath) {
    this.hPath = hPath;
  }


  public String getHContent() {
    return hContent;
  }

  public void setHContent(String hContent) {
    this.hContent = hContent;
  }

  @Override
  public String toString() {
    return "History{" +
            "hid=" + hid +
            ", hForeign='" + hForeign + '\'' +
            ", hPath='" + hPath + '\'' +
            ", hContent='" + hContent + '\'' +
            '}';
  }
}
