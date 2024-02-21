using QAManagement.Filters;
using QAManagement.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;

namespace QAManagement.Controllers
{

    public class TeacherController : Controller
    {
        private QAManagementEntities db = new QAManagementEntities();
        // GET: Teacher
        [RoleAuthorization("Teacher")]
        public ActionResult Index()
        {
            return View();
        }

        [RoleAuthorization("Teacher","Admin")]
        public ActionResult SendForApproval(int? id)
        {
            QuestionPaper PendingQuestionPaper = db.QuestionPapers.Where(q => q.QuestionPaperID == id).FirstOrDefault();
            PendingQuestionPaper.Status = "Pending";
            db.SaveChanges();
            if (Convert.ToString(Session["UserRole"]) == "Admin")
            {
                return RedirectToAction("Index","Admin");
            } else
            {
                return RedirectToAction("Index");
            }
            

        }
        [RoleAuthorization("Teacher")]
        public ActionResult QuestionPapers()
        {
            var TeacherId = Convert.ToInt32(Session["UserId"]);
            var MyQuestionPapers = db.QuestionPapers.Where(q => q.CreatorID == TeacherId).ToList();

            return View(MyQuestionPapers);
        }

        [RoleAuthorization("Teacher")]
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            QuestionPaper questionPaper = db.QuestionPapers.Find(id);
            if (questionPaper == null)
            {
                return HttpNotFound();
            }
            return View(questionPaper);
        }

        [RoleAuthorization("Teacher")]
        public ActionResult Create()
        {
            ViewBag.CreatorID = new SelectList(db.Users, "UserID", "Username");
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "QuestionPaperID,Title,Description,CreationDate,Status,CreatorID")] QuestionPaper questionPaper)
        {
            if (ModelState.IsValid)
            {
                var NewQuestionPaper = new QuestionPaper
                {
                    QuestionPaperID = questionPaper.QuestionPaperID,
                    Title = questionPaper.Title,
                    Description = questionPaper.Description,
                    CreationDate = DateTime.Now,
                    Status = "Created",
                    CreatorID = Convert.ToInt32(Session["UserId"]),

                };// Set current date and time
                db.QuestionPapers.Add(NewQuestionPaper);
                db.SaveChanges();
                if (Convert.ToString(Session["UserRole"]) == "Teacher")
                {
                    return RedirectToAction("Index", "Teacher");
                }
                return RedirectToAction("Index");
            }

            ViewBag.CreatorID = new SelectList(db.Users, "UserID", "Username", questionPaper.CreatorID);
            return View(questionPaper);
        }

        [RoleAuthorization("Teacher")]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            QuestionPaper questionPaper = db.QuestionPapers.Find(id);
            if (questionPaper == null)
            {
                return HttpNotFound();
            }
            ViewBag.CreatorID = new SelectList(db.Users, "UserID", "Username", questionPaper.CreatorID);
            return View(questionPaper);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "QuestionPaperID,Title,Description,CreationDate,Status,CreatorID")] QuestionPaper questionPaper)
        {
            if (ModelState.IsValid)
            {
                var NewQuestionPaper = new QuestionPaper
                {
                    QuestionPaperID = questionPaper.QuestionPaperID,
                    Title = questionPaper.Title,
                    Description = questionPaper.Description,
                    CreationDate = DateTime.Now,
                    Status = "Pending",
                    CreatorID = Convert.ToInt32(Session["UserId"]),

                };
                db.Entry(NewQuestionPaper).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.CreatorID = new SelectList(db.Users, "UserID", "Username", questionPaper.CreatorID);
            return View(questionPaper);
        }
        [RoleAuthorization("Teacher")]
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            QuestionPaper questionPaper = db.QuestionPapers.Find(id);
            if (questionPaper == null)
            {
                return HttpNotFound();
            }
            return View(questionPaper);
        }

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {

            //removing answers of this questionPaper
            var queAnswers = db.Answers.Where(q => q.QuestionPaperID == id);
            foreach (var ans in queAnswers)
            {
                db.Answers.Remove(ans);
            }
            db.SaveChanges();

            //remove each question of this Questionpaper
            var questions = db.Questions.Where(q => q.QuestionPaperID == id);
            foreach (var que in questions)
            {
                db.Questions.Remove(que);
            }
            db.SaveChanges();

            //then remove this QuestionPaper
            QuestionPaper questionPaper = db.QuestionPapers.Find(id);
            db.QuestionPapers.Remove(questionPaper);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}