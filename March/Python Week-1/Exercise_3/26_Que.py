import datetime

today = datetime.date.today()

first_day_of_month = today.replace(day=1)

first_monday = first_day_of_month + datetime.timedelta(days=(7 - first_day_of_month.weekday()))

dates_of_current_month = [first_monday]

for i in range(1, 7):
    dates_of_current_month.append(first_monday + datetime.timedelta(days=i))

print("Dates of the current month starting from Monday to Sunday:")
for date in dates_of_current_month:
    print(date)
