package com.javaserver.demo1.repository;

import com.javaserver.demo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories("es.uc3m.tiw.dominios")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

