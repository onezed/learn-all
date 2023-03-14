package com.onezed.learn.security.model.po;

import lombok.Data;

/**
 * <p/>
 * 数据库的表名: sect_user (security user)
 * <p/>
 *
 * @author lianwenjie
 */
@Data
public class UserPO {

    private Long userId;

    private String username;

    private String userPassword;

    private String userRole;

}
