-- (1) Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.
-- Ex: Write SQL to find IPs that made more than 100 requests starting from 2017-01-01.13:00:00 to 2017-01-01.14:00:00.


SELECT ip 
FROM (
    SELECT ip, COUNT(1) count
    FROM `Request` 
    WHERE `requestDate` BETWEEN '2017-01-01.13:00:00' AND '2017-01-01.14:00:00'
    GROUP BY ip
) requestCount 
WHERE requestCount.count > 100;


-- (2) Write MySQL query to find requests made by a given IP.

SELECT * 
FROM `Request` 
WHERE `ip` = '192.168.234.82';
 	
