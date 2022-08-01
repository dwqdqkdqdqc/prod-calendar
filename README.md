# Production calendar
- Получение нужной даты производственного календаря. Требуется в параметрах передать дату в ISO формате и количество рабочих дней.
  
  Например,
  /calendar/get?input=2022-07-29T00:00:00&count=15

- Сохранение одной даты.
- Сохранение всего списка праздничных и выходных дней. Требуется в параметрах передать JSON. Например:

      [
       {"date": "2023-01-01T00:00:00"},
       {"date": "2023-01-02T00:00:00"},
       {"date": "2023-01-03T00:00:00"},
       {"date": "2023-01-04T00:00:00"},
       {"date": "2023-01-05T00:00:00"},
       {"date": "2023-01-06T00:00:00"},
       {"date": "2023-01-07T00:00:00"}
      ]