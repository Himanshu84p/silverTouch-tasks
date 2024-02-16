using CodeFirstCRUD.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace CodeFirstCRUD.Controllers
{
    public class PublisherController : Controller
    {
        BookContext db = new BookContext();
        // GET: Publisher
        public ActionResult Index()
        {
            var data = db.publishers.ToList();
            return View(data);
        }

        public ActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Create(Publishers p)
        {
            if (ModelState.IsValid == true)
            {
                db.publishers.Add(p);
                int a = db.SaveChanges();
                if (a > 0)
                {
                    TempData["InsertMessage"] = "<script>alert('Data inserted Successfully')</script>";
                    return RedirectToAction("Index");
                }
                else
                {
                    ViewBag.InsertMessage = "<script>alert('Something was wrong')</script>";
                }
            }
            return View();

        }

        public ActionResult Edit(int id)
        {
            var row = db.publishers.Where(model => model.Id == id).FirstOrDefault();
            return View(row);
        }

        [HttpPost]
        public ActionResult Edit(Publishers p)
        {
            if (ModelState.IsValid == true)
            {

                db.Entry(p).State = EntityState.Modified;
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
                var publisherRow = db.publishers.Where(model => model.Id == id).FirstOrDefault();
                if (publisherRow != null)
                {
                    db.Entry(publisherRow).State = EntityState.Deleted;
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
            var publisherRow = db.publishers.Where(model => model.Id == id).FirstOrDefault();
            return View(publisherRow);
        }
    }
}