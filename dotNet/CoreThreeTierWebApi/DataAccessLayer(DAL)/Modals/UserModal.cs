using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessLayer_DAL_.Modals
{
    public class UserModal
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public string? Name { get; set; }
        [Required]

        public string? Email { get; set; }
        [Required]

        public int? Age { get; set; }
        [Required]

        public string? Address { get; set; }
        [Required]

        public string? Phone { get; set; }
        [Required]

        public string? Gender { get; set; }
       
    }
}
