class Student:
    name = None
    reg_no = None
    roll_no = None
    standard = None
    admission_year = None
    marks = []
    result = None

    def __init__(self, name, reg_no, roll_no, standard, admission_year):
        if name.isalpha() == False:
            print("Enter Valid name!!")
            return
        if reg_no.isalnum() == False:
            print("Enter Valid Reg no!!")
            return
        if roll_no.isnumeric() == False:
            print("Enter Valid roll no!!")
            return
        if standard.isnumeric() == False:
            print("Enter Valid standard!!")
            return
        if admission_year.isnumeric() == False:
            print("Enter Valid admission year!!")
            return

        self.name = name
        self.reg_no = reg_no
        self.roll_no = roll_no
        self.standard = standard
        self.admission_year = admission_year

    def assign_marks(self, marks_dict):
        for subject, marks in marks_dict.items():
            if marks <= 100:
                self.marks.append(
                    {subject: marks, "result": "Pass" if marks >= 40 else "Fail"}
                )
        

    def generate_result(self):
        if(self.name == None or self.reg_no == None or self.roll_no  == None or self.standard == None or self.admission_year == None):
            print("Invalid Input in data can not print result!!")
            return

        fail = False
        for mark in self.marks:
            if mark['result'] == "Fail":
                fail = True
                break

        print("*" * 70)
        print("Name:", self.name)
        print("Roll No: %-20s Standard: %-20s " % (self.roll_no, self.standard))
        print("*" * 70)
        print("Subject\t\tTotal Marks\tPassing Marks\tObtained Marks\tResult")
        total_marks = 0
        obtained_marks = 0
        
        for mark in self.marks:
            
            subject, marks, result = list(mark.keys())[0], list(mark.values())[0], mark['result']
            total_marks += 100
            obtained_marks += marks
            print(f"{subject}\t\t100\t\t40\t\t{marks}\t\t{result}")
        print("*" * 70)
        print(f"TOTAL\t\t{total_marks}\t\t\t\t",obtained_marks)
        print("Result:", "FAIL" if fail else "PASS")
        percentage = (obtained_marks / total_marks) * 100 if total_marks != 0 else 0
        print("Percentage: {:.2f}%".format(percentage))

student = Student("Himanshu", "ABC123", "101", "10", "2022")
student.assign_marks({"Math": 85, "Science": 70, "English": 90})
student.generate_result()
