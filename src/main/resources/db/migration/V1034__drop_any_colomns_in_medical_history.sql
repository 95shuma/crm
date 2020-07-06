ALTER TABLE `treatments` add column medical_history_id LONG after type;
ALTER TABLE `sick_lists` add column medical_history_id LONG after end_date;
ALTER TABLE `directions` add column medical_history_id LONG after position_id;
ALTER TABLE `diagnose_results` add column medical_history_id LONG after state;
ALTER TABLE `examination_results` add column medical_history_id LONG after general_state;

alter table `medical_histories` drop `sick_list_id`;
alter table `medical_histories` drop `treatment_id`;
alter table `medical_histories` drop `direction_id`;
alter table `medical_histories` drop `diagnose_result_id`;
alter table `medical_histories` drop `examination_result_id`;
