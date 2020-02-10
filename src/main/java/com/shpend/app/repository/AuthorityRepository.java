package com.shpend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

import com.shpend.app.security.Authority;

public interface AuthorityRepository extends JpaRepository<Authority,Long>{

}
