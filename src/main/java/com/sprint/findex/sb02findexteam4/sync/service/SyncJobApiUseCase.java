package com.sprint.findex.sb02findexteam4.sync.service;

import com.sprint.findex.sb02findexteam4.sync.dto.SyncJobHistoryDto;
import java.time.Instant;
import java.util.List;

public interface SyncJobApiUseCase {

  void syncAll();

  List<SyncJobHistoryDto> syncIndexInfoFromApi();

  List<SyncJobHistoryDto> syncIndexDataFromApi(Instant baseDateFrom, Instant baseDateTo);

}
