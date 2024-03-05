import datetime

today = datetime.date.today()
one_month_from_today = today + datetime.timedelta(days=30)

print("Today's date:", today)
print("Date 1 month from today:", one_month_from_today)
