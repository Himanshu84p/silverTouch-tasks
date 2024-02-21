using QAManagement.Filters;
using QAManagement.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QAManagement.Controllers
{
    public class AdminController : Controller
    {
        private QAManagementEntities db = new QAManagementEntities();
        // GET: Admin
        [RoleAuthorization("Admin")]
        public ActionResult Index()
        {
            return View();
        }

        [RoleAuthorization("Admin")]
        public ActionResult Pending() {
            var pendingQuestionPapers = db.QuestionPapers.Where(q => q.Status == "Pending").ToList();
            return View(pendingQuestionPapers);
        }
        [RoleAuthorization("Admin")]
        public ActionResult RejectPaper(int? id)
        {
            QuestionPaper rejectedQuestionPaper = db.QuestionPapers.Where(q => q.QuestionPaperID == id).FirstOrDefault();
            rejectedQuestionPaper.Status = "Rejected";
            db.SaveChanges();
            return RedirectToAction("Pending");
        }
        [RoleAuthorization("Admin")]
        public ActionResult ApprovePaper(int? id)
        {
            QuestionPaper rejectedQuestionPaper = db.QuestionPapers.Where(q => q.QuestionPaperID == id).FirstOrDefault();
            rejectedQuestionPaper.Status = "Approved";
            db.SaveChanges();
            return RedirectToAction("Pending");
        }
        
    }
   
}