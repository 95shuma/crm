ALTER TABLE `records_journal` add column medical_history_id LONG after `registrar_id`;

alter table `medical_histories` drop `record_journal_id`;

