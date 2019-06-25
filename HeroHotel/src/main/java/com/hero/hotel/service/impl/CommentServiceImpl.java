package com.hero.hotel.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.hero.hotel.dao.CommentDao;
import com.hero.hotel.pojo.Comment;
import com.hero.hotel.pojo.User;
import com.hero.hotel.service.CommentService;

public class CommentServiceImpl implements CommentService {
	@Resource
	private CommentDao commentDao;
	public CommentDao getCommentDao() {
		return commentDao;
	}
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	//分页查询所有评论
	@Override
	public List<Comment> findAll(Integer PageNum) {
		List<Comment> comments=commentDao.findAll(PageNum);
		return comments;
	}
//总条数
	@Override
	public Integer findTotal() {
		
		return commentDao.findAllNumber();
	}
	//添加评论
	@Override
	public Boolean addComment(Comment comment, HttpSession session) {
		//数据校验
		
		if(comment.getMessage()==""||comment.getMessage()==null||comment.getName()==""||comment.getName()==null) {
			return false;
		}
		
		//获取session中的userid
		User user=(User) session.getAttribute("user");
		if(user==null) {
			return false;
		}
		comment.setUserid(user.getId());
		//创建时间
		Date time=new Date();
		comment.setCreatetime(time);
		//获取订单号
		
		//添加到数据库中
		Boolean b=commentDao.addComment(comment);
		return b;
	}

}
