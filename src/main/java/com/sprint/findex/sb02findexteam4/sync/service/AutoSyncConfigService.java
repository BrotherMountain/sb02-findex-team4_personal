package com.sprint.findex.sb02findexteam4.sync.service;

import com.sprint.findex.sb02findexteam4.index.info.entity.IndexInfo;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigDto;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigUpdateCommand;

public interface AutoSyncConfigService extends AutoSyncConfigFindUseCase {
  AutoSyncConfigDto create(IndexInfo indexInfo);

  AutoSyncConfigDto create(IndexInfo indexInfo, boolean enabled);

  AutoSyncConfigDto update(AutoSyncConfigUpdateCommand command);

  void deleteById(Long id);

  void deleteByIndexInfoId(Long indexInfoId);

}
