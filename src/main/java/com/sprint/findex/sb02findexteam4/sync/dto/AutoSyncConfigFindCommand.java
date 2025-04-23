package com.sprint.findex.sb02findexteam4.sync.dto;

import lombok.Builder;

@Builder
public record AutoSyncConfigFindCommand(
    Long indexInfoId,
    boolean enabled,
    Long idAfter,
    String cursor,
    String sortField,
    String sortDirection,
    int size
) {

  public static AutoSyncConfigFindCommand from(Long indexInfoId, boolean enabled, Long idAfter,
      String cursor, String sortField, String sortDirection, int size) {
    return AutoSyncConfigFindCommand.builder()
        .indexInfoId(indexInfoId)
        .enabled(enabled)
        .idAfter(idAfter)
        .cursor(cursor)
        .sortField(sortField)
        .sortDirection(sortDirection)
        .size(size)
        .build();
  }

}
