select *
from thesisspringapp.thesis
where EXTRACT(YEAR FROM thesisspringapp.thesis.create_date) = 2024;

select EXTRACT(YEAR FROM thesisspringapp.thesis.create_date) as 'Year' , avg(thesisspringapp.thesis.score) as 'Avg Score'
from thesisspringapp.thesis
where EXTRACT(YEAR FROM thesisspringapp.thesis.create_date) = 2024
group by EXTRACT(YEAR FROM thesisspringapp.thesis.create_date);

select thesisspringapp.faculty.`name` as 'Tên ngành' , count(thesisspringapp.faculty.id) as 'Tần xuất tham gia'
from thesisspringapp.`user`
inner join thesisspringapp.faculty on thesisspringapp.`user`.faculty_id = thesisspringapp.faculty.id
inner join thesisspringapp.thesis_user on thesisspringapp.`user`.id = thesisspringapp.thesis_user.user_id
inner join thesisspringapp.thesis on thesisspringapp.thesis.id = thesisspringapp.thesis_user.thesis_id
where EXTRACT(YEAR FROM thesisspringapp.thesis.create_date) = 2024 and thesisspringapp.`user`.`role_id` = 2
group by thesisspringapp.faculty.`name`;	
