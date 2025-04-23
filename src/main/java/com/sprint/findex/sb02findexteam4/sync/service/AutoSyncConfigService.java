package com.sprint.findex.sb02findexteam4.sync.service;

import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigDto;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigUpdateCommand;

public interface AutoSyncConfigService extends AutoSyncConfigFindUseCase {

  AutoSyncConfigDto update(AutoSyncConfigUpdateCommand command);

}
