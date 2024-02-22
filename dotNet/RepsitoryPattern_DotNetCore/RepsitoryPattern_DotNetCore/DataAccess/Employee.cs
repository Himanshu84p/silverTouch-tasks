using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace RepsitoryPattern_DotNetCore.DataAccess;

public partial class Employee
{
    public int Id { get; set; }

    [Required(ErrorMessage = "First Name is required")]
    [StringLength(50, ErrorMessage = "First Name must be less than 50 characters")]
    public string FirstName { get; set; }

    [Required(ErrorMessage = "Last Name is required")]
    [StringLength(50, ErrorMessage = "Last Name must be less than 50 characters")]
    public string LastName { get; set; }

    [StringLength(100, ErrorMessage = "Job Title must be less than 100 characters")]
    public string JobTitle { get; set; }

    [StringLength(100, ErrorMessage = "Department must be less than 100 characters")]
    public string Department { get; set; }
}
