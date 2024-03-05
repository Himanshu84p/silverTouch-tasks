import datetime

today = datetime.date.today()

first_day_of_month = today.replace(day=1)

last_day_of_month = first_day_of_month.replace(month=first_day_of_month.month % 12 + 1, day=1) - datetime.timedelta(days=1)

formatted_first_date = first_day_of_month.strftime("%d%S %B %Y %A %I:%M:%S %p").replace('0th', 'th')
formatted_last_date = last_day_of_month.strftime("%d%S %B %Y %A %I:%M:%S %p").replace('0th', 'th')

print("First date of the current month:", formatted_first_date)
print("Last date of the current month:", formatted_last_date)
