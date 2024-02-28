using System.ComponentModel.DataAnnotations;

namespace OrderManagementAPI.Modals
{
    public class CustomerModal
    {
        [Key]
        public int CustomerId { get; set; }
        [Required]

        public string? Name { get; set; }
        [Required]
        [EmailAddress(ErrorMessage = "Enter Valid Email Address")]

        public string? Email { get; set; }
        [Required]

        public string? Address { get; set; }

        public bool? IsDeleted { get; set; }
        [Required]
        [StringLength(8)]

        public string? Password { get; set; }

        public string? Role { get; set; }
    }
}
