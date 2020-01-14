package com.shpend.app.service;

import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.User;

public class UpdateUserThread implements Runnable{
	
	private User user;
	private Teacher teacher;
	public UpdateUserThread(User user, Teacher teacher) {
		this.user = user;
		this.teacher = teacher;
	}

	@Override
	public void run() {
		user.setTeacher(teacher);
		user.setCompletedInfo((Integer) 1);
		System.out.println("Here run");
	}
}
