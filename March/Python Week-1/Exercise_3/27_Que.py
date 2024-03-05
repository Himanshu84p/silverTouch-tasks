import datetime

today = datetime.date.today()

first_day_of_month = today.replace(day=1)

last_day_of_month = first_day_of_month.replace(month=first_day_of_month.month % 12 + 1, day=1) - datetime.timedelta(days=1)

print("First date of the current month:", first_day_of_month)
print("Last date of the current month:", last_day_of_month)
