package com.sprint.findex.sb02findexteam4.sync.repository;

import com.sprint.findex.sb02findexteam4.sync.entity.AutoSyncConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoSyncConfigRepository extends JpaRepository<AutoSyncConfig, Long> {

  boolean existsByIndexInfo_Id(Long indexInfoId);

  void deleteByIndexInfo_Id(Long indexInfoId);
}
