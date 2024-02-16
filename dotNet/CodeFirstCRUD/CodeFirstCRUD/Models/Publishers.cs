using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace CodeFirstCRUD.Models
{
    public class Publishers
    {
        [Key]
        public int Id { get; set; }

        [Required(ErrorMessage = "PublisherName is Required")]
        public string PublisherName { get; set; }
        [Required(ErrorMessage = "Date is Required")]
        public string Founded { get; set; }
        [Required(ErrorMessage = "Location is Required")]
        public string Location { get; set; }
        [Required]
        [RegularExpression(@"^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$", ErrorMessage = "Invalid email format")]
        public string Email { get; set; }
        [Required]
        [RegularExpression(@"^\d{10}$", ErrorMessage = "Invalid mobile number format")]
        public string Phone { get; set; }
    }
}