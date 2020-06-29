alter table `sick_lists` drop `medical_history_id`;
alter table `treatments` drop `medical_history_id`;
alter table `directions` drop `medical_history_id`;
alter table `diagnose_results` drop `medical_history_id`;
alter table `examination_results` drop `medical_history_id`;