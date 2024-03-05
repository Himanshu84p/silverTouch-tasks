import datetime

today = datetime.date.today()
one_year_from_today = today + datetime.timedelta(days=365)

print("Today's date:", today)
print("Date 1 year from today:", one_year_from_today)
