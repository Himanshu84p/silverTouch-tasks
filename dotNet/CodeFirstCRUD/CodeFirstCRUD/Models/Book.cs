using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace CodeFirstCRUD.Models
{
    public class Book
    {
        [Key]
        public int Id { get; set; }

        [Required(ErrorMessage = "Title is Required")]
        public string Title { get; set; }
        [Required(ErrorMessage = "Author is Required")]
        public string Author { get; set; }
        [Required(ErrorMessage = "Genre is Required")]
        public string Genre { get; set; }
        [Required(ErrorMessage = "Date is Required")]

        public DateTime Published { get; set; }
        [Required(ErrorMessage = "Price is Required")]

        public decimal Price { get; set; }

    }
}