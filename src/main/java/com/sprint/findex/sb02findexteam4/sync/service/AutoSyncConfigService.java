package com.sprint.findex.sb02findexteam4.sync.service;

import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigDto;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigFindCommand;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigUpdateCommand;
import com.sprint.findex.sb02findexteam4.sync.dto.CursorPageResponseAutoSyncConfigDto;

public interface AutoSyncConfigService {

  AutoSyncConfigDto update(AutoSyncConfigUpdateCommand command);

  CursorPageResponseAutoSyncConfigDto findAll(AutoSyncConfigFindCommand command);

}
