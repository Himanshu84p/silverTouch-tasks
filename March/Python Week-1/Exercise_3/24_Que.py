import datetime

today = datetime.date.today()

first_day_of_month = datetime.date(today.year, today.month, 1)

print("Today's date:", today)
print("1st day of the current month:", first_day_of_month)
