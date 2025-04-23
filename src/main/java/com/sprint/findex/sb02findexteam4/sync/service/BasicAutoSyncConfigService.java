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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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

  @Override
  @Transactional(readOnly = true)
  public CursorPageResponseAutoSyncConfigDto findAll(AutoSyncConfigFindCommand command) {
    Pageable pageable = createPage(command.sortDirection(), command.sortField(), command.size());

    Slice<AutoSyncConfigDto> slice = autoSyncConfigRepository.findAll(pageable)
        .map(AutoSyncConfigDto::of);

    return fromSlice(slice, command.sortField());
  }

  @Override
  @Transactional(readOnly = true)
  public CursorPageResponseAutoSyncConfigDto findAllByInfoId(AutoSyncConfigFindCommand command) {
    Pageable pageable = createPage(command.sortDirection(), command.sortField(), command.size());
    Slice<AutoSyncConfigDto> slice = autoSyncConfigRepository.findAllByIndexInfo_Id(
        command.indexInfoId(), pageable).map(AutoSyncConfigDto::of);

    return fromSlice(slice, command.sortField());
  }

  @Override
  @Transactional(readOnly = true)
  public CursorPageResponseAutoSyncConfigDto findAllByEnabled(AutoSyncConfigFindCommand command) {
    Pageable pageable = createPage(command.sortDirection(), command.sortField(), command.size());

    Slice<AutoSyncConfigDto> slice = autoSyncConfigRepository.findAllByEnabled(command.enabled(),
        pageable).map(AutoSyncConfigDto::of);

    return fromSlice(slice, command.sortField());
  }

  @Override
  @Transactional(readOnly = true)
  public CursorPageResponseAutoSyncConfigDto findAllByInfoIdAndEnabled(
      AutoSyncConfigFindCommand command) {
    Pageable pageable = createPage(command.sortDirection(), command.sortField(), command.size());
    Slice<AutoSyncConfigDto> slice = autoSyncConfigRepository.findAllByIndexInfoIdAndEnabled
            (command.indexInfoId(), command.enabled(), pageable)
        .map(AutoSyncConfigDto::of);
    return fromSlice(slice, command.sortField());
  }

  private Pageable createPage(String sortDirection, String sortField, int size) {
    Sort.Direction Direction = Sort.Direction.fromString(sortDirection);
    Sort sort = Sort.by(Direction, sortField);
    return PageRequest.of(0, size, sort);
  }

  private CursorPageResponseAutoSyncConfigDto fromSlice(
      Slice<AutoSyncConfigDto> slice,
      String sortField) {
    String nextCursor = null;
    Long nextIdAfter = null;
    if (!slice.getContent().isEmpty()) {
      int lastIndex = slice.getNumberOfElements() - 1;
      AutoSyncConfigDto configDto = slice.getContent().get(lastIndex);
      nextIdAfter = configDto.id();

      if ("enabled".equals(sortField)) {
        nextCursor = String.valueOf(configDto.enabled());
      } else if ("indexInfo.indexName".equals(sortField)) {
        nextCursor = configDto.id().toString();
      }
    }

    return new CursorPageResponseAutoSyncConfigDto(slice.getContent(), nextCursor, nextIdAfter,
        slice.getSize(), (long) slice.getNumberOfElements(), slice.hasNext());
  }

}
