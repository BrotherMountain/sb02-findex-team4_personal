package com.sprint.findex.sb02findexteam4.sync.dto;

import com.sprint.findex.sb02findexteam4.sync.entity.AutoSyncConfig;
import lombok.Builder;

@Builder
public record AutoSyncConfigDto(
    Long id,
    Long indexInfoId,
    String indexClassification,
    String indexName,
    boolean enabled
) {

  public static AutoSyncConfigDto of(AutoSyncConfig config) {
    return AutoSyncConfigDto.builder()
        .id(config.getId())
        .enabled(config.isEnabled())
        .indexInfoId(config.getIndexInfo().getId())
        .indexClassification(config.getIndexInfo().getIndexClassification())
        .indexName(config.getIndexInfo().getIndexName()).build();
  }
}
