package com.earthdaily.pos.data;

import com.earthdaily.pos.api.contract.v1.Pos;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PosRepository extends JpaRepository<Pos, String> {
}
