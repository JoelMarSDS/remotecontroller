package com.cuscuzcomjava.remotecontroller.repository;

import com.cuscuzcomjava.remotecontroller.entity.Actress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActressRepository extends JpaRepository <Actress,Long> {

    Actress findByLogin(String login);
}
