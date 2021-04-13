package cn.com.teacher.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wx
 */
@Data
@ToString
public class History implements Serializable {

    /**
     * 历史记录表id
     */
    private Integer h_id;

    /**
     * 关联用户信息表id
     */
    private String h_forrign;

    /**
     * 路径
     */
    private String h_path;

    /**
     * 内容
     */
    private String h_content;

    /**
     * 时间
     */
    private String h_time;

    /**
     * 标签
     */
    private String h_label;

}
