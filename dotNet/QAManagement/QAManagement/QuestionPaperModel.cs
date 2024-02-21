using QAManagement.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QAManagement
{
    public class QuestionPaperViewModel
    {
        public QuestionPaper QuestionPaper { get; set; }
        public bool IsAttempted { get; set; }
    }
}