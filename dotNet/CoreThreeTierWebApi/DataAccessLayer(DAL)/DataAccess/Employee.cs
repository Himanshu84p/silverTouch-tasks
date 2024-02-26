using System;
using System.Collections.Generic;

namespace DataAccessLayer_DAL_.DataAccess;

public partial class Employee
{
    public int Id { get; set; }

    public string? FirstName { get; set; }

    public string? LastName { get; set; }

    public string? JobTitle { get; set; }

    public string? Department { get; set; }
}
