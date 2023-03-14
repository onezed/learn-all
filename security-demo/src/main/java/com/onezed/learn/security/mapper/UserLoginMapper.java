package com.onezed.learn.security.mapper;

import com.onezed.learn.security.model.po.UserPO;
import com.onezed.learn.security.model.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p/>
 * Describe
 * <p/>
 *
 * @author lianwenjie
 */
@Mapper
public interface UserLoginMapper {

    UserPO selectUser(@Param("vo") LoginVO loginVO);

    UserPO selectUserOnToken(@Param("token") String token);
}
