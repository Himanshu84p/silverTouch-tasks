class Student:
    Name = False
    RegNo = False
    RollNo = False
    Standard = False
    AdmissionYear = False
    Marks = []
    Result = False
    
    def __init__(self, name: str, regNo, rollNo: str, standard: str, admissionYear: str) -> None:
        self.Result = "PASS"
        self.Name = name
        self.RegNo = regNo
        self.RollNo = rollNo
        self.Standard = standard
        self.AdmissionYear = admissionYear
        if not (name.isalnum() and regNo.isalnum()):
            raise Exception("Name and RegNo must be alphanumeric")
        if not (rollNo.isnumeric() and standard.isnumeric() and admissionYear.isnumeric()):
            raise Exception("RollNo, Standard, and Admission Year must be numeric")   

    def addMarks(self, dict: dict):
        for key, value in dict.items():
            if value > 100:
                raise Exception("Marks must be less than or equal to 100")
            else:
                self.Marks.append({
                    "Subject": key,
                    "Mark": value,
                })
                if value < 40:
                    self.Result = "FAIL"

    def calculateGrade(result, percentage) -> str:
        if result == "FAIL":
            return "F"
        elif percentage >= 95:
            return "O+"
        elif percentage >= 90:
            return "O"
        elif percentage >= 85:
            return "A+"
        elif percentage >= 80:
            return "A"
        elif percentage >= 75:
            return "B+"
        elif percentage >= 70:
            return "B"
        elif percentage >= 60:
            return "C"
        elif percentage >= 50:
            return "D"
        elif percentage >= 40:
            return "E"
        else:
            return "F"


    def printResult(self):
        totalMarks = 0
        passingMarks = 0
        obtainedMarks = 0
        countSubject = 0
        
        print("*******************************************************************")
        print(f"Name : {self.Name}")
        print(f"Roll No : {self.RollNo}                           Standard: {self.Standard}")
        print("*******************************************************************")
        print("Subject  ||  Total Marks  ||  Passing Marks  ||  Obtained Marks  ||  Result")
        for obj in self.Marks:
            totalMarks += 100
            passingMarks += 40
            obtainedMarks += obj["Mark"]
            countSubject += 1
            print(f"{obj['Subject']}  ||  100  ||  40  ||  {obj['Mark']}  ||  {'Fail' if obj['Mark'] < 40 else 'Pass'}")
        print("*******************************************************************")
        print(f"Total Subjects: {countSubject}  ||  Total Marks: {totalMarks}  ||  Passing Marks: {passingMarks}  ||  Obtained Marks: {obtainedMarks}")
        print(f"Result: {self.Result}                                    Percentage: {'--' if self.Result == 'FAIL' else str(round(obtainedMarks / totalMarks * 100, 2))+"%"}")
        print(f"Grade: {Student.calculateGrade(self.Result,round(obtainedMarks / totalMarks * 100, 2))}")

himanshu = Student("Himanshu", "ABC123", "6048", "10", "2020")
marks = {
    "Maths": 94,
    "Sceince": 90,
    "English": 92
}
himanshu.addMarks(marks)
himanshu.printResult()
