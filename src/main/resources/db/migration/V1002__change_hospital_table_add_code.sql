CREATE TABLE regions (
  id BIGINT NOT NULL,
  code VARCHAR(256),
  name VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
);
alter table hospitals
	add column region_id BIGINT after name,
    add index IDX_region (region_id),
    add FOREIGN KEY FK_region (region_id) REFERENCES regions (id);

ALTER TABLE hospitals
    DROP COLUMN address;
