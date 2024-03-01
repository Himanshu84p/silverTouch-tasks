using OrderManagementAPI.DataAccess;
using System.ComponentModel.DataAnnotations;

namespace OrderManagementAPI.Modals
{
    public class ProductModal
    {
        [Key]
        public int ProductId { get; set; }
        [Required]

        public string? Name { get; set; }


        public string? Description { get; set; }
        [Required]

        public decimal? Price { get; set; }
        [Required]

        public int? StockQuantity { get; set; }

        public bool? IsDeleted { get; set; }

    }
}
