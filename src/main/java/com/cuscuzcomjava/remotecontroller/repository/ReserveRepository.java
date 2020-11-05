package com.cuscuzcomjava.remotecontroller.repository;

import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Long> {
  List<Reserve> findByActress(Actress actress);

  List<Reserve> findByProducer(Producer producerId);
}
