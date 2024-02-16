using DbFirstTodo.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace DbFirstTodo.Controllers
{
    public class TodoController : Controller
    {
        TodoCodeFirstEntities db = new TodoCodeFirstEntities();
        // GET: Todo
        public ActionResult Index()
        {
            var data = db.TodoItems.ToList();
            return View(data);
        }

        public ActionResult Add()
        {
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Add(TodoItem td)
        {
            var duplicateRow = db.TodoItems.Where(model => model.Task == td.Task && model.IsCompleted == false).FirstOrDefault();
            if (duplicateRow == null)
            {
                if (ModelState.IsValid == true)
                {
                    db.TodoItems.Add(td);
                    int a = db.SaveChanges();
                    if (a > 0)
                    {
                        TempData["InsertMessage"] = "Todo inserted Successfully";
                        return RedirectToAction("Index");
                    }
                    else
                    {
                        ViewBag.InsertMessage = "Bad Request";
                    }
                }
            }
            else
            {
                TempData["DuplicateError"] = "<script>alert('Duplicate data found Insert Different');</script>";
                return RedirectToAction("Index");
            }
            return View();
        }

        [HttpPost]
        public ActionResult Delete(int? id)
        {
            if (id > 0)
            {
                var todoRow = db.TodoItems.Where(model => model.Id == id).FirstOrDefault();
                if (todoRow != null)
                {
                    db.Entry(todoRow).State = EntityState.Deleted;
                    int a = db.SaveChanges();
                    if (a > 0)
                    {
                        TempData["DeletedMessage"] = "Todo Deleted successfully!!";
                        return RedirectToAction("Index");
                    }
                    else
                    {
                        TempData["DeletedMessage"] = "Todo not deleted";
                    }
                }
            }
            return View(db);
        }

        [HttpPost]
        public ActionResult Complete(int id, bool isCompleted)
        {
            var todoItem = db.TodoItems.Find(id);

            if (todoItem != null)
            {
                todoItem.IsCompleted = isCompleted;
                db.SaveChanges();
            }
            return RedirectToAction("Index");
        }
    }
}