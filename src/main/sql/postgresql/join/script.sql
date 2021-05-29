select c.model, i.name, i.exp_date from cars as c join insurances as i on c.insurance_id = i.id;
select c.number as Номер, i.exp_date as Страховка from cars as c join insurances as i on c.insurance_id = i.id and i.exp_date < current_date;
select c.model as Модель, c.number as Номер, i.exp_date as Страховка from cars as c join insurances as i on c.insurance_id = i.id;