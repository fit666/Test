package com.hero.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.hero.hotel.pojo.User;
import com.hero.hotel.pojo.Vip;


public interface UserDao {
	
	//查询所有用户
	@Select("select * from t_user")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="account",property="account"),
		@Result(column="tel",property="tel"),
		@Result(column="createtime",property="createtime"),
		@Result(column="monetary",property="monetary"),
		@Result(column="infoid",property="info",
				one=@One(select="com.hero.hotel.dao.UserInforDao.findById")
		),
		@Result(column="monetary",property="vip",
				one=@One(select="com.hero.hotel.dao.VipDao.findById")
		)
	})
	public List<User> findAll();
	
	//查询数据库用户名（注册或登录时使用）
	@Select("select * from t_user where account=#{account} and flag=1")
	public User findAccountByAccount(User user);
	
	//插入注册的信息到数据库（注册时使用）
	@Insert("insert into t_user(account,password,tel,createtime,roleid) values(#{account},#{password},#{tel},#{createtime},#{roleid})")
	public boolean insertAccount(User user);

	//查询账户通过手机号
	@Select("select * from t_user where tel=#{tel} and flag=1")
	public User findUserByTel(User user);
	
	//查vip信息
	@Select("select * from t_vip where id=#{id} and flag=1 ")
	public Vip findVipByID(Integer id);
}