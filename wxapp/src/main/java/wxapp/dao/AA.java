package wxapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wxapp.entity.User;

@Service
@Transactional
public class AA implements A {
	@Autowired
	Dao em;

	@Override
	public void init() {
		User user = new User();
		user.name = "RR";
		int id = em.merge(user).id;
		user = em.find(User.class, id);
		System.out.println(user.name);
		em.remove(user);
	}

}
