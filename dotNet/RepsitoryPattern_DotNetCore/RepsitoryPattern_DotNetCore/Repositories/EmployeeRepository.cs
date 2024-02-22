using RepsitoryPattern_DotNetCore.DataAccess;
using RepsitoryPattern_DotNetCore.Interfaces;
using System.Runtime.ExceptionServices;

namespace RepsitoryPattern_DotNetCore.Repositories
{
    public class EmployeeRepository : IEmployee
    {
        private readonly CoreScaffoldDbContext context;

        public EmployeeRepository(CoreScaffoldDbContext context)
        {
            this.context = context;
        }
    
        public IEnumerable<Employee> GetEmployees()
        {
            var data = context.Employees.ToList();
            return data;
        }
        
        
        public void Edit(Employee employee)
        {
            if(employee != null)
            {
                context.Employees.Update(employee);
                context.SaveChanges();
            }
        }

        public Employee GetEmp(int? id)
        {
            var emp = context.Employees.Find(id);
            return emp;
        }

        public void Delete(Employee employee)
        {
            if (employee != null)
            {
                context.Employees.Remove(employee);
                context.SaveChanges();
            }
        }

        public void Create(Employee employee)
        {
            if (employee != null)
            {
                context.Employees.Add(employee);
                context.SaveChanges();
            } else
            {
                throw new Exception();
            }
        }
    }
}
