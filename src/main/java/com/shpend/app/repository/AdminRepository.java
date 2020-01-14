package com.shpend.app.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shpend.app.domain.Admin;
import com.shpend.app.domain.Admin;
import com.shpend.app.domain.User;
import com.shpend.app.service.UserService;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
