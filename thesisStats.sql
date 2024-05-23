select *
from thesisspringapp.thesis
where EXTRACT(YEAR FROM thesisspringapp.thesis.create_date) = 2024;

select EXTRACT(YEAR FROM thesisspringapp.thesis.create_date) as 'Year' , avg(thesisspringapp.thesis.score) as 'Avg Score'
from thesisspringapp.thesis
where EXTRACT(YEAR FROM thesisspringapp.thesis.create_date) = 2024
group by EXTRACT(YEAR FROM thesisspringapp.thesis.create_date);