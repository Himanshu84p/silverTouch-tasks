using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using CodeFirstCRUD.Models;

namespace CodeFirstCRUD.Controllers
{
    public class BookController : Controller
    {
        BookContext db = new BookContext();
        // GET: Book
        public ActionResult Index()
        {
            var data = db.books.ToList();
            return View(data);
        }

        public ActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Create(Book b)
        {
            if (ModelState.IsValid == true)
            {
                db.books.Add(b);
                int a = db.SaveChanges();
                if (a > 0)
                {
                    TempData["InsertMessage"] = "<script>alert('Data inserted Successfully')</script>";
                    return RedirectToAction("Index");
                } else
                {
                    ViewBag.InsertMessage = "<script>alert('Something was wrong')</script>";
                }
            }
            return View();

        }
        public ActionResult Edit(int id) {
            var row = db.books.Where(model => model.Id == id).FirstOrDefault();
            return View(row);
        }

        [HttpPost]
        public ActionResult Edit(Book b)
        {
            if (ModelState.IsValid == true)
            {
                db.Entry(b).State = EntityState.Modified;
                int a = db.SaveChanges();
                if (a > 0)
                {
                    TempData["UpdateMessage"] = "<script>alert('Data Updated Successfully')</script>";
                    return RedirectToAction("Index");
                }
                else
                {
                    TempData["UpdateMessage"] = "<script>alert('Something was wrong')</script>";
                }
            }
            return View();
        }

        public ActionResult Delete(int id)
        {
            if (id > 0)
            {
                var bookRow = db.books.Where(model => model.Id == id).FirstOrDefault();
                if (bookRow != null)
                {
                    db.Entry(bookRow).State = EntityState.Deleted;
                    int a = db.SaveChanges();
                    if (a > 0)
                    {
                        TempData["DeletedMessage"] = "<script>alert('Data Deleted Successfully')</script>";
                        return RedirectToAction("Index");
                    }
                    else
                    {
                        TempData["DeletedMessage"] = "<script>alert('Data not deleted')</script>";
                    }
                }
            }
            return View(db);
        }
        public ActionResult Details(int id)
        {
            var bookRow = db.books.Where(model => model.Id == id).FirstOrDefault();
            return View(bookRow);
        }
    }
}