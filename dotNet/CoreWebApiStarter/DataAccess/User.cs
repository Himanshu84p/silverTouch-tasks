using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace CoreWebApiStarter.DataAccess;

public partial class User
{
    public int Id { get; set; }
    [Required]
    public string? Name { get; set; }
    [Required]
    [EmailAddress(ErrorMessage = "Enter Valid Email")]

    public string? Email { get; set; }
    [Required]

    public int? Age { get; set; }
    [Required]

    public string? Address { get; set; }
    [Required]
    [StringLength(10)]

    public string? Phone { get; set; }
    [Required]

    public string? Gender { get; set; }
}
