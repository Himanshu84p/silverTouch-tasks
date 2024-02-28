using OrderManagementAPI.DataAccess;
using System.ComponentModel.DataAnnotations;

namespace OrderManagementAPI.Modals
{
    public class OrderModal
    {
        [Key]
        public int OrderId { get; set; }
        [Required]

        public int? CustomerId { get; set; }

        public DateTime? OrderDate { get; set; }
        [Required]

        public decimal? TotalAmount { get; set; }
        [Required]

        public string? Status { get; set; }

        public bool? IsDeleted { get; set; }

        
    }
}
