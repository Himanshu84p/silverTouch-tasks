import datetime

today = datetime.date.today()
one_week_from_today = today + datetime.timedelta(weeks=1)

print("Today's date:", today)
print("Date 1 week from today:", one_week_from_today)
