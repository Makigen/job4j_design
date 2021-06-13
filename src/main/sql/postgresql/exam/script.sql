SELECT p.name, c.name FROM person AS p JOIN company AS c ON p.company_id = c.id AND p.company_id != 5;

WITH j AS (
	SELECT c.name, COUNT(p) AS cnt FROM company AS c
	JOIN person AS p
	ON c.id = p.company_id group by c.name
)
SELECT * FROM j WHERE j.cnt = (SELECT MAX(j.cnt) FROM j);