package cn.com.teacher.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author wx
 */
@Data
@ToString
public class Resources implements Serializable {

    /**
     * 资源表id
     */
    private Integer r_id;

    /**
     * 路径
     */
    private String r_path;

    /**
     * 内容
     */
    private String r_content;

    /**
     * 标签
     */
    private String r_label;

}
