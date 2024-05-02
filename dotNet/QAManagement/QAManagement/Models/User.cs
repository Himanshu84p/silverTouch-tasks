//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace QAManagement.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel;
    using System.ComponentModel.DataAnnotations;

    public partial class User
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public User()
        {
            this.QuestionPapers = new HashSet<QuestionPaper>();
            this.Answers = new HashSet<Answer>();
            this.Approvals = new HashSet<Approval>();
        }

        [Key]
        public int UserID { get; set; }
        [Required(ErrorMessage = "Username Field is required")]
        [MaxLength(50, ErrorMessage = "Length should be less than 50")]
        [MinLength(5, ErrorMessage = "Minimum length should be 5")]
        public string Username { get; set; }
        [Required(ErrorMessage = "Password Field is required")]
        [MinLength(5, ErrorMessage = "Minimum length should be 6")]
        [DisplayName("Password")]
        public string PasswordHash { get; set; }
        [Required(ErrorMessage = "Email Field is required")]
        [EmailAddress(ErrorMessage = "Enter Valid Email Address")]
        public string Email { get; set; }
        [Required(ErrorMessage = "Role Field is required")]
        public string Role { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<QuestionPaper> QuestionPapers { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Answer> Answers { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Approval> Approvals { get; set; }
    }
}
