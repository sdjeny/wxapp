package org.sdjen.apps.service;

import java.util.Date;

import org.sdjen.apps.dao.Dao;
import org.sdjen.apps.entity.User;
import org.sdjen.apps.util.DaoParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AImpl implements A {
	Logger logger = LoggerFactory.getLogger(AImpl.class);
	@Autowired
	Dao dao;

	@Override
	public void init() {
		User user = new User();
		user.name = "RR";
		user.birth = new Date();
		int id = dao.merge(user).id;
		logger.debug("" + id);
		user = dao.find(User.class, id);
		logger.debug("Name：" + user.name);
		logger.debug("birth：" + user.birth);

		logger.debug("select R："
				+ dao.getObject(DaoParams.get().setJpql("select name from User where name=?1").setParams("RR")));
		logger.debug("select E："
				+ dao.getObject(DaoParams.get().setJpql("select name from User where name=?1").setParams("EE")));
		logger.debug("Update："
				+ dao.update(DaoParams.get().setJpql("update User set name=?1 where name=?2").setParams("EE", "RR")));
		logger.debug("select RL：" + dao
				.getList(DaoParams.get().setJpql("select name from User where name=:name").addParams("name", "RR")));
		logger.debug("select EL：" + dao
				.getList(DaoParams.get().setJpql("select name from User where name=:name").addParams("name", "EE")));
		logger.debug("Update："
				+ dao.update(DaoParams.get().setJpql("update User set name=?1 where name=?2").setParams("RR", "EE")));

		user = dao.getObject(DaoParams.get().setJpql("from User where name=?1").setParams("RR"));
		logger.debug("Name：" + user.name);
		logger.debug("birth：" + user.birth);
		dao.remove(user);
	}

}
