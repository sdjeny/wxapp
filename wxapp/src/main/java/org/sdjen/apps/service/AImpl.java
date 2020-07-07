package org.sdjen.apps.service;

import java.util.Date;

import org.sdjen.apps.dao.Dao;
import org.sdjen.apps.entity.User;
import org.sdjen.apps.util.DaoParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AImpl implements A {
	@Autowired
	Dao dao;

	@Override
	public void init() {
		User user = new User();
		user.name = "RR";
		user.birth = new Date();
		int id = dao.merge(user).id;
		System.out.println(id);
		user = dao.find(User.class, id);
		System.out.println("Name：" + user.name);
		System.out.println("birth：" + user.birth);

		System.out.println("select R："
				+ dao.getObject(DaoParams.get().setJpql("select name from User where name=?1").setParams("RR")));
		System.out.println("select E："
				+ dao.getObject(DaoParams.get().setJpql("select name from User where name=?1").setParams("EE")));
		System.out.println("Update："
				+ dao.update(DaoParams.get().setJpql("update User set name=?1 where name=?2").setParams("EE", "RR")));
		System.out.println("select RL：" + dao
				.getList(DaoParams.get().setJpql("select name from User where name=:name").addParams("name", "RR")));
		System.out.println("select EL：" + dao
				.getList(DaoParams.get().setJpql("select name from User where name=:name").addParams("name", "EE")));
		System.out.println("Update："
				+ dao.update(DaoParams.get().setJpql("update User set name=?1 where name=?2").setParams("RR", "EE")));

		user = dao.getObject(DaoParams.get().setJpql("from User where name=?1").setParams("RR"));
		System.out.println("Name：" + user.name);
		System.out.println("birth：" + user.birth);
		dao.remove(user);
	}

}
