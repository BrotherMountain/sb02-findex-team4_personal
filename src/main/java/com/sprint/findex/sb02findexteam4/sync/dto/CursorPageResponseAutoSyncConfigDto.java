package com.sprint.findex.sb02findexteam4.sync.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record CursorPageResponseAutoSyncConfigDto(
    List<AutoSyncConfigDto> content,
    String nextCursor,
    Long nextIdAfter,
    int size,
    Long totalElements,
    boolean hasNext
) {

}
