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
    using System.ComponentModel.DataAnnotations;

    public partial class Answer
    {
        [Key]
        public int AnswerID { get; set; }
        [Required]
        public int UserID { get; set; }
        [Required]
        public int QuestionID { get; set; }
        [Required]
        public string SubmittedAnswer { get; set; }
        public System.DateTime SubmissionTimestamp { get; set; }
        public int QuestionPaperID { get; set; }
    
        public virtual Question Question { get; set; }
        public virtual User User { get; set; }
        public virtual QuestionPaper QuestionPaper { get; set; }
    }
}
