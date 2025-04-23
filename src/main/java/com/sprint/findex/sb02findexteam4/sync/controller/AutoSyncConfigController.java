package com.sprint.findex.sb02findexteam4.sync.controller;

import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigDto;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigFindCommand;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigUpdateCommand;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigUpdateRequest;
import com.sprint.findex.sb02findexteam4.sync.dto.CursorPageResponseAutoSyncConfigDto;
import com.sprint.findex.sb02findexteam4.sync.service.AutoSyncConfigService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auto-sync-configs")
@RequiredArgsConstructor
public class AutoSyncConfigController {

  private final AutoSyncConfigService autoSyncConfigService;

  @PatchMapping("/{id}")
  public ResponseEntity<AutoSyncConfigDto> update(@PathVariable Long id, @RequestBody
  AutoSyncConfigUpdateRequest request) {
    AutoSyncConfigUpdateCommand command = new AutoSyncConfigUpdateCommand(id,
        request.enabled());

    AutoSyncConfigDto result = autoSyncConfigService.update(command);

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @GetMapping
  public ResponseEntity<CursorPageResponseAutoSyncConfigDto> findAll(
      @RequestParam(value = "indexInfoId", required = false) Long indexInfoId,
      @RequestParam(value = "enabled", required = false) boolean enabled,
      @RequestParam(value = "idAfter", required = false) Long idAfter,
      @RequestParam(value = "cursor", required = false) String cursor,
      @RequestParam(value = "sortField", required = false, defaultValue = "indexInfo.indexName") String sortField,
      @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      HttpServletRequest request
  ) {
    AutoSyncConfigFindCommand command = AutoSyncConfigFindCommand.from(indexInfoId, enabled,
        idAfter,
        cursor, sortField, sortDirection,
        size);

    boolean enabledProvided = request.getParameterMap().containsKey("enabled");

    Boolean enabledCond = enabledProvided ? enabled : null;

    CursorPageResponseAutoSyncConfigDto response;
    if (indexInfoId == null && enabledCond == null) {
      response = autoSyncConfigService.findAll(command);
    } else if (indexInfoId == null) {
      response = autoSyncConfigService.findAllByEnabled(command);
    } else if (enabledCond == null) {
      response = autoSyncConfigService.findAllByInfoId(command);
    } else {
      response = autoSyncConfigService.findAllByInfoIdAndEnabled(command);
    }

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
