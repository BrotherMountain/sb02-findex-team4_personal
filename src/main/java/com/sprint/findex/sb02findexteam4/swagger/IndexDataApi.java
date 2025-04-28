package com.sprint.findex.sb02findexteam4.swagger;

import com.sprint.findex.sb02findexteam4.index.data.dto.CursorPageResponseIndexDataDto;
import com.sprint.findex.sb02findexteam4.index.data.dto.IndexChartDto;
import com.sprint.findex.sb02findexteam4.index.data.dto.IndexDataCreateRequest;
import com.sprint.findex.sb02findexteam4.index.data.dto.IndexDataResponse;
import com.sprint.findex.sb02findexteam4.index.data.dto.IndexDataUpdateRequest;
import com.sprint.findex.sb02findexteam4.index.data.dto.IndexPerformanceDto;
import com.sprint.findex.sb02findexteam4.index.data.dto.RankedIndexPerformanceDto;
import com.sprint.findex.sb02findexteam4.index.data.entity.PeriodType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "���� ������ ��� ��ȸ", description = "���� ������ ���� API")
public interface IndexDataApi {

  public interface IndexData {

    @Operation(summary = "���� ������ ��� ��ȸ", description = "���� ������ ����� ��ȸ�մϴ�. ���͸�, ����, Ŀ�� ��� ���������̼��� �����մϴ�.", responses = {
        @ApiResponse(responseCode = "200", description = "���� ������ ��� ��ȸ ����"),
        @ApiResponse(responseCode = "400", description = "�߸��� ��û(��ȿ���� ���� ���� �� ��)"),
        @ApiResponse(responseCode = "500", description = "���� ����")
    })
    ResponseEntity<CursorPageResponseIndexDataDto> getIndexDataList(
        @RequestParam(required = false) Long indexInfoId,
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate,
        @RequestParam(required = false) Long idAfter,
        @RequestParam(required = false) String cursor,
        @RequestParam(defaultValue = "baseDate") String sortField,
        @RequestParam(defaultValue = "desc") String sortDirection,
        @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "���� ������ ���", description = "���ο� ���� �����͸� ����մϴ�.", responses = {
        @ApiResponse(responseCode = "201", description = "���� ������ ���� ����"),
        @ApiResponse(responseCode = "400", description = "�߸��� ��û(��ȿ���� ���� ������ �� ��)"),
        @ApiResponse(responseCode = "404", description = "�����ϴ� ���� ������ ã�� �� ����"),
        @ApiResponse(responseCode = "500", description = "���� ����")})
    ResponseEntity<IndexDataResponse> createIndexData(
        @RequestBody IndexDataCreateRequest request);

    @Operation(summary = "���� ������ ����", description = "���� �����͸� �����մϴ�.", responses = {
        @ApiResponse(responseCode = "204", description = "���� ������ ���� ����"),
        @ApiResponse(responseCode = "404", description = "������ ���� �����͸� ã�� �� ����"),
        @ApiResponse(responseCode = "500", description = "���� ����")})
    ResponseEntity<Void> deleteIndexData(@PathVariable long id);

    @Operation(summary = "���� ������ ����", description = "���� ���� �����͸� �����մϴ�.", responses = {
        @ApiResponse(responseCode = "200", description = "���� ������ ���� ����"),
        @ApiResponse(responseCode = "400", description = "�߸��� ��û(��ȿ���� ���� ������ �� ��)"),
        @ApiResponse(responseCode = "404", description = "������ ���� �����͸� ã�� �� ����"),
        @ApiResponse(responseCode = "500", description = "���� ����")})
    ResponseEntity<IndexDataResponse> updateIndexData(@PathVariable Long id,
        @RequestBody IndexDataUpdateRequest indexDataRequest);

    @Operation(summary = "���� ��Ʈ ��ȸ", description = "������ ��Ʈ �����͸� ��ȸ�մϴ�.", responses = {
        @ApiResponse(responseCode = "200", description = "��Ʈ ������ ���� ����"),
        @ApiResponse(responseCode = "400", description = "�߸��� ��û(��ȿ���� ���� �Ⱓ ���� ��)"),
        @ApiResponse(responseCode = "404", description = "���� ������ ã�� �� ����"),
        @ApiResponse(responseCode = "500", description = "���� ����")})
    ResponseEntity<IndexChartDto> getChartData(
        @PathVariable("id") Long indexInfoId,
        @RequestParam(value = "periodType", defaultValue = "DAILY") PeriodType periodType);

    @Operation(summary = "���� ���� ��ŷ ��ȸ", description = "������ ���� �м� ��ŷ�� ��ȸ�մϴ�.", responses = {
        @ApiResponse(responseCode = "200", description = "���� ��ŷ ��ȸ ����"),
        @ApiResponse(responseCode = "400", description = "�߸��� ��û(��ȿ���� ���� �Ⱓ ���� ��)"),
        @ApiResponse(responseCode = "500", description = "���� ����")})
    ResponseEntity<List<RankedIndexPerformanceDto>> getPerformanceRank(
        @RequestParam(required = false) Long indexInfoId,
        @RequestParam(defaultValue = "DAILY") PeriodType periodType,
        @RequestParam(defaultValue = "10") int limit
    );

    @Operation(summary = "���� ���� ���� ��ȸ", description = "���ã��� ��ϵ� �������� ������ ��ȸ�մϴ�.", responses = {
        @ApiResponse(responseCode = "200", description = "���� ���� ���� ��ȸ ����"),
        @ApiResponse(responseCode = "500", description = "���� ����")})
    ResponseEntity<List<IndexPerformanceDto>> getFavoriteIndexPerformances(
        @RequestParam(defaultValue = "DAILY") PeriodType periodType);

    @Operation(summary = "���� ������ CSV export", description = "���� �����͸� CSV ���Ϸ� export�մϴ�.", responses = {
        @ApiResponse(responseCode = "200", description = "CSV ���� ���� ����"),
        @ApiResponse(responseCode = "400", description = "�߸��� ��û(��ȿ���� ���� ���� �� ��"),
        @ApiResponse(responseCode = "500", description = "���� ����")})
    ResponseEntity<byte[]> exportCsv(
        @RequestParam(required = false) Long indexInfoId,
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate,
        @RequestParam(defaultValue = "baseDate") String sortField,
        @RequestParam(defaultValue = "desc") String sortDirection
    );
  }
}
