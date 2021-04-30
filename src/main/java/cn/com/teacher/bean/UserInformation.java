package cn.com.teacher.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wx
 */
@Data
@ToString
public class UserInformation implements Serializable {

    /**
     * 用户id
     */
    private Integer u_id;

    /**
     * 用户账号
     */
    private String u_number;

    /**
     * 用户密码
     */
    private String u_password;

    /**
     * 用户头像
     */
    private String u_head;

    /**
     * 用户昵称
     */
    private String u_name;

    /**
     * 0 表示普通用户 1表示管理员
     */
    private String u_state;

    /**
     * 用户上次登录事件时间
     */
    private String u_time;

    /**
     * 更新人账号
     */
    private String u_update_number;

    /**
     * 更新时间
     */
    private String u_update_time;

    /**
     * 0 表示不可见 1表示可见
     */
    private String u_visual;
}
