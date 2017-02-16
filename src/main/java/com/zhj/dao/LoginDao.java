package com.zhj.dao;

import com.zhj.dao.base.BaseDao;
import com.zhj.vo.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class LoginDao extends BaseDao {

    /**
     * 条件查询用户表
     *
     * @param user
     * @return
     */
    public List<User> getUserByCondition(User user) {
        StringBuilder sb = new StringBuilder();
        List values = new ArrayList();
        sb.append("FROM com.zhj.vo.User u WHERE 1=1 ");
        if (user != null) {
            if (null != user.getId()) {
                sb.append("AND id=? ");
                values.add(user.getId());
            }
            if (null != user.getUsername() && !"".equals(user.getUsername().trim())) {
                sb.append("AND u.username=? ");
                values.add(user.getUsername());
            }
            if (null != user.getPassword() && !"".equals(user.getPassword().trim())) {
                sb.append("AND password=? ");
                values.add(user.getPassword());
            }
            if (null != user.getNickname() && !"".equals(user.getNickname())) {
                sb.append("AND nickname LIKE %?% ");
                values.add(user.getNickname());
            }
            if (null != user.getAddress() && !"".equals(user.getAddress())) {
                sb.append("AND address LIKE %?% ");
                values.add(user.getAddress());
            }
        }
        return (List<User>) findByHql(sb.toString(), values.toArray(), null);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    public int addUser(User user) {
        return 0;
    }
}
