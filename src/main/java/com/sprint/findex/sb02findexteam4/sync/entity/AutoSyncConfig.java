package com.sprint.findex.sb02findexteam4.sync.entity;

import com.sprint.findex.sb02findexteam4.indexInfo.IndexInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "index_auto_sync")
@Entity
public class AutoSyncConfig {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "index_info_id", nullable = false)
  private IndexInfo indexInfo;

  @Column(name = "enabled", nullable = false)
  private boolean enabled;

  private AutoSyncConfig(IndexInfo indexInfo) {
    this.indexInfo = indexInfo;
    this.enabled = false;
  }

  private AutoSyncConfig(IndexInfo indexInfo, boolean enabled) {
    this.indexInfo = indexInfo;
    this.enabled = enabled;
  }

  public static AutoSyncConfig create(IndexInfo indexInfo) {
    return new AutoSyncConfig(indexInfo);
  }

  public static AutoSyncConfig create(IndexInfo indexInfo, boolean enabled) {
    return new AutoSyncConfig(indexInfo, enabled);
  }

  public void update(boolean newEnabled) {
    this.enabled = newEnabled;
  }

  public void enableAutoSync() {
    this.enabled = true;
  }

  public void disableAutoSync() {
    this.enabled = false;
  }

  public boolean isAutoSyncEnabled() {
    return enabled;
  }

}
