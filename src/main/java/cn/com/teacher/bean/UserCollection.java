package cn.com.teacher.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wx
 */
@Data
@ToString
public class UserCollection implements Serializable {

    /**
     * 收藏表id
     */
    private Integer c_id;

    /**
     * 路径
     */
    private String c_path;

    /**
     * 关联用户信息表id
     */
    private String c_foreign;

    /**
     * 内容
     */
    private String c_content;

    /**
     * 标签
     */
    private String c_label;


}
