
-- 1. List all countries along with their region names.
select c.COUNTRY_ID, c.COUNTRY_NAME, c.REGION_ID, r.REGION_NAME  from countries c
join regions r
on c.REGION_ID = r.REGION_ID;

-- 2. List all locations along with their country names.
select l.LOCATION_ID, l.STREET_ADDRESS, l.POSTAL_CODE, l.CITY, l.STATE_PROVINCE, l.COUNTRY_ID , c.COUNTRY_NAME from locations l
join countries c
on l.COUNTRY_ID = c.COUNTRY_ID;

-- 3. Find all regions, including those without any countries.
select r.REGION_ID, r.REGION_NAME , c.COUNTRY_ID, c.COUNTRY_NAME from regions r
left join countries c
on r.REGION_ID = c.REGION_ID;

-- 4. Find all countries, including those without any locations.
select c.COUNTRY_ID, c.COUNTRY_NAME, l.LOCATION_ID, l.CITY from countries c
left join locations l
on c.COUNTRY_ID = l.COUNTRY_ID;

-- 5. Get the count of countries in each region.
select r.REGION_NAME, count(c.COUNTRY_ID) Count_of_Countries  from regions r
left join countries c
on r.REGION_ID = c.REGION_ID
group by r.REGION_ID, .REGION_NAME;

-- 6. Get the count of locations in each country.
select c.COUNTRY_NAME, count(LOCATION_ID) Count_of_locations  from countries c
left join locations l
on c.COUNTRY_ID = l.COUNTRY_ID
group by c.COUNTRY_ID, c.COUNTRY_NAME;

-- 7. List regions that have more than 5 countries.
select r.REGION_NAME, count(c.COUNTRY_ID) Count_of_Countries  from regions r
left join countries c
on r.REGION_ID = c.REGION_ID
group by r.REGION_ID, r.REGION_NAME
having Count_of_Countries > 5;

-- 8. Find all cities with their country and region names.
select l.CITY, c.COUNTRY_NAME, r.REGION_NAME from locations l
join countries c
on l.COUNTRY_ID = c.COUNTRY_ID
join regions r
on c.REGION_ID = r.REGION_ID;

-- 9. List all countries that do not have any locations.
select c.COUNTRY_NAME, l.LOCATION_ID, l.CITY from countries c
left join locations l
on c.COUNTRY_ID = l.COUNTRY_ID
where l.COUNTRY_ID is null;

-- 10. List the region name, country name, and the number of locations per country.
select r.REGION_NAME, c.COUNTRY_NAME , count(l.LOCATION_ID) Count_of_Locations from countries c
join regions r
on c.REGION_ID = r.REGION_ID
left join locations l
on c.COUNTRY_ID = l.COUNTRY_ID
group by c.COUNTRY_NAME, r.REGION_NAME;

--  
-- 11. Which countries are located in the "Asia" region?
select c.COUNTRY_NAME from countries c
join regions r
on c.REGION_ID = r.REGION_ID
where r.REGION_NAME = 'Asia';

-- 12. List the names of all countries in the "Americas" region that have at least one location.
select c.COUNTRY_NAME  from countries c
join regions r
on c.REGION_ID = r.REGION_ID
left join locations l
on c.COUNTRY_ID = l.COUNTRY_ID
group by c.COUNTRY_NAME, r.REGION_NAME
having count(l.LOCATION_ID) >= 1 and r.REGION_NAME = 'Americas';

SELECT DISTINCT
    c.COUNTRY_NAME
FROM countries c
JOIN regions r ON c.REGION_ID = r.REGION_ID
JOIN locations l ON c.COUNTRY_ID = l.COUNTRY_ID
WHERE r.REGION_NAME = 'Americas';
-- 13. Find all cities in the "Europe" region along with their respective country names.
select l.CITY, c.COUNTRY_NAME, r.REGION_NAME from locations l
join countries c
on l.COUNTRY_ID = c.COUNTRY_ID
join regions r
on c.REGION_ID = r.REGION_ID
where r.REGION_NAME = 'Europe';

-- 14. How many countries are in the "Middle East and Asia" region?
select r.REGION_NAME, count(c.COUNTRY_ID) Count_of_Countries from countries c
left join regions r
on c.REGION_ID = r.REGION_ID
group by r.REGION_NAME
having r.REGION_NAME = 'Middle East and Asia';

-- 15. List all regions along with the number of countries in each region.
select r.REGION_NAME, count(c.COUNTRY_ID)  Count_of_Countries from countries c
left join regions r
on c.REGION_ID = r.REGION_ID
group by r.REGION_NAME;

-- 16. Which countries do not have any associated locations?
select c.COUNTRY_NAME, l.LOCATION_ID, l.CITY from countries c
left join locations l
on c.COUNTRY_ID = l.COUNTRY_ID
where l.COUNTRY_ID is null;

-- 17. Find all countries along with their region names, where the region name is either "Europe" or "Asia".
select c.COUNTRY_NAME, r.REGION_NAME from countries c
join regions r
on c.REGION_ID = r.REGION_ID
where r.REGION_NAME = 'Europe' or r.REGION_NAME = 'Asia';

-- 18. List all locations in "Italy" along with the city and postal code.
select c.COUNTRY_NAME, l.LOCATION_ID, l.CITY, l.POSTAL_CODE from locations l
left join countries c
on l.COUNTRY_ID = c.COUNTRY_ID
where c.COUNTRY_NAME = 'Italy';

-- 19. Which countries have more than one location?
select c.COUNTRY_NAME from countries c
join locations l
on c.COUNTRY_ID = l.COUNTRY_ID
group by c.COUNTRY_NAME
having count(l.COUNTRY_ID) > 1;

-- 20 Retrieve all locations in "Canada" and the United States along with the state/province information.
select c.COUNTRY_NAME, l.LOCATION_ID, l.CITY, l.POSTAL_CODE, l.STATE_PROVINCE from locations l
 join countries c
on l.COUNTRY_ID = c.COUNTRY_ID
where c.COUNTRY_NAME = 'Canada' or c.COUNTRY_NAME = 'USA';