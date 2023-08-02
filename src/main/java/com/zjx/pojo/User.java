package com.zjx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String password;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 角色枚举
     */
    private Integer role;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 注册时间
     */
    private Date registDate;
}
