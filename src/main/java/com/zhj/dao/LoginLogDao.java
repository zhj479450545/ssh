package com.zhj.dao;

import com.zhj.vo.LoginLogVo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class LoginLogDao {
	
	public int insertUserLoginLog(LoginLogVo loginLogVo) {
		return 0;
	}
	
	/**
	 * 按条件查询列表
	 * @param loginLogVo
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<LoginLogVo> findUserLoginLogs(LoginLogVo loginLogVo, Integer pageIndex, Integer pageSize) {
		return null;
	}

	/**
	 * 按条件查询总条数
	 * @param loginLogVo
	 * @return
	 */
	public int findCountUserLoginLogs(LoginLogVo loginLogVo) {
		return 0;
	}
}
