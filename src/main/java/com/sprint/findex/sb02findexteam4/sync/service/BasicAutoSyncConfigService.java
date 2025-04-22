package com.sprint.findex.sb02findexteam4.sync.service;

import com.sprint.findex.sb02findexteam4.exception.ErrorCode;
import com.sprint.findex.sb02findexteam4.exception.InvalidRequestException;
import com.sprint.findex.sb02findexteam4.exception.NotFoundException;
import com.sprint.findex.sb02findexteam4.exception.SystemException;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigDto;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigFindCommand;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigUpdateCommand;
import com.sprint.findex.sb02findexteam4.sync.dto.CursorPageResponseAutoSyncConfigDto;
import com.sprint.findex.sb02findexteam4.sync.entity.AutoSyncConfig;
import com.sprint.findex.sb02findexteam4.sync.repository.AutoSyncConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BasicAutoSyncConfigService implements AutoSyncConfigService {

  private final AutoSyncConfigRepository autoSyncConfigRepository;

  @Override
  @Transactional
  public AutoSyncConfigDto update(AutoSyncConfigUpdateCommand command) {
    try {
      AutoSyncConfig autoSyncConfig = autoSyncConfigRepository.findById(command.id()).orElseThrow(
          () -> new NotFoundException(ErrorCode.AUTO_SYNC_NOT_FOUND)
      );
      autoSyncConfig.update(command.enabled());
      return AutoSyncConfigDto.of(autoSyncConfig);
    } catch (IllegalArgumentException e) {
      throw new InvalidRequestException(ErrorCode.AUTO_SYNC_BAD_REQUEST);
    } catch (RuntimeException e) {
      throw new SystemException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public CursorPageResponseAutoSyncConfigDto findAll(AutoSyncConfigFindCommand command) {
    return null;
  }
}
