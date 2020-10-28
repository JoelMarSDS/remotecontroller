package com.cuscuzcomjava.remotecontroller.repository;

import com.cuscuzcomjava.remotecontroller.entity.Secrets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretsRepository extends JpaRepository<Secrets,Long> {
}
