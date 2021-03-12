package cn.com.teacher.bean;

public class UserInformation {

  private long uid;
  private String uNumber;
  private String uPassword;
  private String uHead;
  private String uName;


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getUNumber() {
    return uNumber;
  }

  public void setUNumber(String uNumber) {
    this.uNumber = uNumber;
  }


  public String getUPassword() {
    return uPassword;
  }

  public void setUPassword(String uPassword) {
    this.uPassword = uPassword;
  }


  public String getUHead() {
    return uHead;
  }

  public void setUHead(String uHead) {
    this.uHead = uHead;
  }


  public String getUName() {
    return uName;
  }

  public void setUName(String uName) {
    this.uName = uName;
  }

  @Override
  public String toString() {
    return "UserInformation{" +
            "uid=" + uid +
            ", uNumber='" + uNumber + '\'' +
            ", uPassword='" + uPassword + '\'' +
            ", uHead='" + uHead + '\'' +
            ", uName='" + uName + '\'' +
            '}';
  }
}
