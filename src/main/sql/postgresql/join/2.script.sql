select avg(price) from devices;

select pp.name, avg(d.price)
	from people as pp 
	join devices_people as dp 
		on pp.id = dp.people_id 
	join devices as d 
		on d.id = dp.device_id 
	group by pp.name;
	
select pp.name, avg(d.price) 
	from people as pp 
	join devices_people as dp 
		on pp.id = dp.people_id 
	join devices as d 
		on d.id = dp.device_id 
	group by pp.name
	having avg(d.price) > 5000;