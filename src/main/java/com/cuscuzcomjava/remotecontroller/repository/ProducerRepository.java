package com.cuscuzcomjava.remotecontroller.repository;

import com.cuscuzcomjava.remotecontroller.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer,Long> {
}
