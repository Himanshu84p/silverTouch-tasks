using RepsitoryPattern_DotNetCore.DataAccess;
using System.Security.Cryptography;

namespace RepsitoryPattern_DotNetCore.Interfaces
{
    public interface IEmployee
    {
        public IEnumerable<Employee> GetEmployees();

        public Employee GetEmp(int? id);
        public void Create(Employee employee);
        
        public void Edit(Employee employee);
        public void Delete(Employee employee);
    }
}
