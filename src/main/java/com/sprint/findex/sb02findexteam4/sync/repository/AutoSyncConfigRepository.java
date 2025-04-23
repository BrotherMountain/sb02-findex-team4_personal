package com.sprint.findex.sb02findexteam4.sync.repository;

import com.sprint.findex.sb02findexteam4.sync.entity.AutoSyncConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoSyncConfigRepository extends JpaRepository<AutoSyncConfig, Long> {

  Slice<AutoSyncConfig> findAllByIndexInfo_Id(Long indexInfoId, Pageable pageable);

  Slice<AutoSyncConfig> findAllByEnabled(boolean enabled, Pageable pageable);

  Slice<AutoSyncConfig> findAllByIndexInfoIdAndEnabled(Long indexInfoId, boolean enabled,
      Pageable pageable);

}
