package com.raf.sk_user_service.repository;

import com.raf.sk_user_service.domain.Trening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreningRepository extends JpaRepository<Trening, Long> {
}
