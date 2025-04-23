package com.sprint.findex.sb02findexteam4.sync.service;

import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigFindCommand;
import com.sprint.findex.sb02findexteam4.sync.dto.CursorPageResponseAutoSyncConfigDto;

public interface AutoSyncConfigFindUseCase {

  CursorPageResponseAutoSyncConfigDto findAll(AutoSyncConfigFindCommand command);

  CursorPageResponseAutoSyncConfigDto findAllByInfoId(AutoSyncConfigFindCommand command);

  CursorPageResponseAutoSyncConfigDto findAllByEnabled(AutoSyncConfigFindCommand command);

  CursorPageResponseAutoSyncConfigDto findAllByInfoIdAndEnabled(
      AutoSyncConfigFindCommand command);
}
