package com.sprint.findex.sb02findexteam4.sync.service;

import com.sprint.findex.sb02findexteam4.exception.ErrorCode;
import com.sprint.findex.sb02findexteam4.exception.InvalidRequestException;
import com.sprint.findex.sb02findexteam4.exception.NotFoundException;
import com.sprint.findex.sb02findexteam4.exception.SystemException;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigDto;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigUpdateCommand;
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
  public AutoSyncConfigDto update(
      AutoSyncConfigUpdateCommand command) {
    try {
      //autoSyncConfig 있는지 확인, 없으면 not found 에러 발생
      AutoSyncConfig autoSyncConfig = autoSyncConfigRepository.findById(command.id()).orElseThrow(
          () -> new NotFoundException(ErrorCode.AUTO_SYNC_NOT_FOUND)
      );
      //enabled 상태를 업데이트함
      autoSyncConfig.update(command.enabled());

      //build 패턴을 이용해서 dto 감싸기
      return AutoSyncConfigDto.of(autoSyncConfig);

      //예상치 못한 인수가 들어오면 bad request 에러 발생
    } catch (IllegalArgumentException e) {
      throw new InvalidRequestException(ErrorCode.AUTO_SYNC_BAD_REQUEST);

      //시스템이 중간에 뻗는 등이 일이 발생하면 internal server 에러 발생
    } catch (RuntimeException e) {
      throw new SystemException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }
}
