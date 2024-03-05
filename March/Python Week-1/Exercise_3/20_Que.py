from datetime import date

dob = date(day=25,month=11,year=2002)

today = date.today()

age = today.year - dob.year - ((today.month, today.day) < (dob.month, dob.day))

if today.month < dob.month or (today.month == dob.month and today.day < dob.day):
        months = 12 - dob.month + today.month - 1
else:
    months = today.month - dob.month
days = (today - date(today.year, today.month - 1, dob.day)).days
    
print(f"age is {age}, month {months} and days {days}")