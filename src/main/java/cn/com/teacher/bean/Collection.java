package cn.com.teacher.bean;


public class Collection {

  private long cId;
  private String cPath;
  private String cForeign;
  private String cContent;


  public long getCId() {
    return cId;
  }

  public void setCId(long cId) {
    this.cId = cId;
  }


  public String getCPath() {
    return cPath;
  }

  public void setCPath(String cPath) {
    this.cPath = cPath;
  }


  public String getCForeign() {
    return cForeign;
  }

  public void setCForeign(String cForeign) {
    this.cForeign = cForeign;
  }


  public String getCContent() {
    return cContent;
  }

  public void setCContent(String cContent) {
    this.cContent = cContent;
  }

  @Override
  public String toString() {
    return "Collection{" +
            "cId=" + cId +
            ", cPath='" + cPath + '\'' +
            ", cForeign='" + cForeign + '\'' +
            ", cContent='" + cContent + '\'' +
            '}';
  }
}
