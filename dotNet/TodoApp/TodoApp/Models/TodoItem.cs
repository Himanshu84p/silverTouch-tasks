using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace TodoApp.Models
{
    public class TodoItem
    {
        [Key]
        public int Id { get; set; }
        [Required]
        [StringLength(100)]
        public string Task { get; set; }
        public bool IsCompleted { get; set; }
    }
}