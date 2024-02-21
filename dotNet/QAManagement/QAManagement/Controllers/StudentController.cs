using QAManagement.Filters;
using QAManagement.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QAManagement.Controllers
{
    [RoleAuthorization("Student")]
    public class StudentController : Controller
    {
        QAManagementEntities db = new QAManagementEntities();
        // GET: Student
        
        public ActionResult Index()
        {
            int userId = Convert.ToInt32(Session["UserId"]);
            var questionPapers = db.QuestionPapers.Where(q => q.Status == "Approved").ToList();
            var questionPaperViewModels = new List<QuestionPaperViewModel>();

            foreach (var questionPaper in questionPapers)
            {
                bool isAttempted = db.Answers.Any(a => a.UserID == userId && a.QuestionPaperID == questionPaper.QuestionPaperID);
                var viewModel = new QuestionPaperViewModel
                {
                    QuestionPaper = questionPaper,
                    IsAttempted = isAttempted
                };
                questionPaperViewModels.Add(viewModel);
            }

            return View(questionPaperViewModels);
        }


        public ActionResult Attempt(int id)
        {
            // Retrieve the question paper with the given id
            var questionPaper = db.QuestionPapers.Find(id);
            ViewBag.QuestionPaperID = id;

            if (questionPaper == null)
            {
                return HttpNotFound();
            }

            // Retrieve questions for the question paper
            var questions = db.Questions.Where(q => q.QuestionPaperID == id).ToList();

            // Pass the question paper and its questions to the Attempt view
            ViewBag.QuestionPaper = questionPaper;
            return View(questions);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Submit(int questionPaperID, FormCollection form)
        {

            int userID = Convert.ToInt32(Session["UserId"]);

            foreach (var key in form.AllKeys)
            {
                // Assuming input names are in the format "answer_<questionID>"
                if (key.StartsWith("answer_"))
                {
                    int questionID = int.Parse(key.Substring("answer_".Length));
                    string submittedAnswer = form[key];

                    // Save the submitted answer to the Answers table
                    var answer = new Answer
                    {
                        UserID = userID,
                        QuestionID = questionID,
                        QuestionPaperID = questionPaperID, // Set the QuestionPaperID
                        SubmittedAnswer = submittedAnswer,
                        SubmissionTimestamp = DateTime.Now
                    };

                    db.Answers.Add(answer);
                }
            }

            // Save changes to the database
            db.SaveChanges();

            // Redirect to a success page or back to the index page
            return RedirectToAction("Index", "Student");
        }

    }
}