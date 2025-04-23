package com.sprint.findex.sb02findexteam4.sync.controller;

import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigDto;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigUpdateCommand;
import com.sprint.findex.sb02findexteam4.sync.dto.AutoSyncConfigUpdateRequest;
import com.sprint.findex.sb02findexteam4.sync.service.AutoSyncConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
