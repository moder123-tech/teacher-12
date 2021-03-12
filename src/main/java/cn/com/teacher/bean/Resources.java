package cn.com.teacher.bean;


public class Resources {

  private long rId;
  private String rPath;
  private String rContent;
  private String rLabel;


  public long getRId() {
    return rId;
  }

  public void setRId(long rId) {
    this.rId = rId;
  }


  public String getRPath() {
    return rPath;
  }

  public void setRPath(String rPath) {
    this.rPath = rPath;
  }


  public String getRContent() {
    return rContent;
  }

  public void setRContent(String rContent) {
    this.rContent = rContent;
  }


  public String getRLabel() {
    return rLabel;
  }

  public void setRLabel(String rLabel) {
    this.rLabel = rLabel;
  }

  @Override
  public String toString() {
    return "Resources{" +
            "rId=" + rId +
            ", rPath='" + rPath + '\'' +
            ", rContent='" + rContent + '\'' +
            ", rLabel='" + rLabel + '\'' +
            '}';
  }
}
