ALTER TABLE `examination_results`
add column instrum_examination_result VARCHAR(256) after instrum_examination_id,
add column general_state varchar (256) after instrum_examination_result;
