# SQlexample
An example of different SQL queries.
To check the functionality of App just run Main() in MainSQL.Class after compilation

==========================================================================

-------------------------
Answers for the problems:
-------------------------

*******Problem 1*********

SELECT * FROM gorod WHERE region_id = #{region_id} ORDER BY naselenie DESC //region_id =1

*******Problem 2*********

SELECT COUNT(*) FROM gorod WHERE naselenie IS NULL 

*******Problem 3*********

SELECT * FROM gorod WHERE naselenie = (SELECT MAX(naselenie) from gorod) 

*******Problem 4*********

Delete from gorod where naselenie < #{population} //population = 400000

*******Problem 5*********

Update gorod set naselenie = #{naselenie} where region_id = #{region_id} //region_id = 1

*******Problem 6*********

select * from gorod WHERE city_name LIKE 'К%' 

*******problem 7*********

SELECT r.id, r.region_name, g.cnt FROM region r LEFT JOIN (SELECT COUNT(region_id) as cnt, region_id FROM gorod GROUP BY region_id) g ON r.id = g.region_id 

-------------------------



