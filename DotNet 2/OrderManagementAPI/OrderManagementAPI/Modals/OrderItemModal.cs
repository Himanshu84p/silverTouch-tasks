using System.ComponentModel.DataAnnotations;

namespace OrderManagementAPI.Modals
{
    public class OrderItemModal
    {
        [Key]
        public int OrderItemId { get; set; }
        [Required]

        public int? OrderId { get; set; }
        [Required]

        public int? ProductId { get; set; }
        [Required]

        public int? Quantity { get; set; }
        [Required]

        public decimal? UnitPrice { get; set; }

        public bool? IsDeleted { get; set; }

    }
}
