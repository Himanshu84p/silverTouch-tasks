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
    public class QuestionsController : Controller
    {
        private QAManagementEntities db = new QAManagementEntities();

        // GET: Questions
        [RoleAuthorization("Admin","Teacher")]
        public ActionResult Index(int id)
        {
            ViewBag.Qid = id;
            Session["ForQuestionPaperId"] = id;
            var questions = db.Questions.Where(q => q.QuestionPaperID == id);
            return View(questions.ToList());
        }

        // GET: Questions/Details/5
        [RoleAuthorization("Admin", "Teacher")]
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Question question = db.Questions.Find(id);
            if (question == null)
            {
                return HttpNotFound();
            }
            return View(question);
        }

        // GET: Questions/Create
        [RoleAuthorization("Admin", "Teacher")]
        public ActionResult Create(int? id)
        {
            ViewBag.QuestionPaperID = new SelectList(db.QuestionPapers, "QuestionPaperID", "Title");
            return View();
        }

        // POST: Questions/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "QuestionID,QuestionText,Option1,Option2,Option3,Option4,CorrectAnswer")] Question question,int id)
        {
            if (ModelState.IsValid)
            {
                
                question.QuestionPaperID = id;
                db.Questions.Add(question);
                db.SaveChanges();
                return RedirectToAction("Index", new {id});
            }

            ViewBag.QuestionPaperID = new SelectList(db.QuestionPapers, "QuestionPaperID", "Title", question.QuestionPaperID);
            return View(question);
        }

        // GET: Questions/Edit/5
        [RoleAuthorization("Admin", "Teacher")]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Question question = db.Questions.Find(id);
            if (question == null)
            {
                return HttpNotFound();
            }
            ViewBag.QuestionPaperID = new SelectList(db.QuestionPapers, "QuestionPaperID", "Title", question.QuestionPaperID);
            return View(question);
        }

        // POST: Questions/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "QuestionID,QuestionText,Option1,Option2,Option3,Option4,CorrectAnswer")] Question question,int id)
        {
            if (ModelState.IsValid)
            {
                question.QuestionPaperID = id;
                db.Entry(question).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index", new {id});
            }
            ViewBag.QuestionPaperID = new SelectList(db.QuestionPapers, "QuestionPaperID", "Title", question.QuestionPaperID);
            return View(question);
        }

        // GET: Questions/Delete/5
        [RoleAuthorization("Admin", "Teacher")]
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Question question = db.Questions.Find(id);
            if (question == null)
            {
                return HttpNotFound();
            }
            return View(question);
        }

        // POST: Questions/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            //removing answers of this question
            var queAnswers = db.Answers.Where(q => q.QuestionID == id);
            foreach (var ans in queAnswers)
            {
                db.Answers.Remove(ans);
            }
            db.SaveChanges();

            //removing Question
            Question question = db.Questions.Find(id);
            db.Questions.Remove(question);
            db.SaveChanges();
            return RedirectToAction("Index", new {id});
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
