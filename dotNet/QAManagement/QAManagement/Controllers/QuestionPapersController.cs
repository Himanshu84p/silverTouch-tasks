using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using QAManagement.Filters;
using QAManagement.Models;

namespace QAManagement.Controllers
{
    
    public class QuestionPapersController : Controller
    {
        private QAManagementEntities db = new QAManagementEntities();

        // GET: QuestionPapers
        [RoleAuthorization("Admin")]
        public ActionResult Index()
        {
            var questionPapers = db.QuestionPapers.Include(q => q.User);
            return View(questionPapers.ToList());
        }

        // GET: QuestionPapers/Details/5
        [RoleAuthorization("Teacher", "Admin")]
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

        // GET: QuestionPapers/Create
        [RoleAuthorization("Teacher", "Admin")]
        public ActionResult Create()
        {
            ViewBag.CreatorID = new SelectList(db.Users, "UserID", "Username");
            return View();
        }

        // POST: QuestionPapers/Create
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
                    return RedirectToAction("Index","Teacher");
                }
                return RedirectToAction("Index");
            }

            ViewBag.CreatorID = new SelectList(db.Users, "UserID", "Username", questionPaper.CreatorID);
            return View(questionPaper);
        }

        // GET: QuestionPapers/Edit/5
        [RoleAuthorization("Teacher", "Admin")]
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

        // POST: QuestionPapers/Edit/5
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

        // GET: QuestionPapers/Delete/5
        [RoleAuthorization("Teacher", "Admin")]
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

        // POST: QuestionPapers/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {

            //removing answers of this questionaPaper
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
