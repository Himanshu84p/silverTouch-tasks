using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace CodeFirstCRUD.Models
{
    public class Member
    {
        [Key]
        public int Id { get; set; }

        [Required(ErrorMessage = "Name is Required")]
        public string Name { get; set;}
        [Required]
        [RegularExpression(@"^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$", ErrorMessage = "Invalid email format")]
        public string Email { get; set;}
        [Required]
        [RegularExpression(@"^\d{10}$", ErrorMessage = "Invalid mobile number format")]
        public string Phone { get; set;}
        [Required(ErrorMessage = "Gender is Required")]
        public string Gender { get; set;}

    }
}