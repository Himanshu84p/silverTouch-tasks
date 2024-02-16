using System;
using System.Collections.Generic;
using System.Linq;
using System.Data.Entity;
using System.Web;
using System.Web.Mvc;
using CodeFirstCRUD.Models;

namespace CodeFirstCRUD.Controllers
{
    public class MemberController : Controller
    {
        BookContext db = new BookContext();
        // GET: Member
        public ActionResult Index()
        {
            var data = db.members.ToList();
            return View(data);
        }

        public ActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Create(Member m)
        {
            if (ModelState.IsValid == true)
            {
                db.members.Add(m);
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
            var row = db.members.Where(model => model.Id == id).FirstOrDefault();
            return View(row);
        }

        [HttpPost]
        public ActionResult Edit(Member m)
        {
            if (ModelState.IsValid == true)
            {

                db.Entry(m).State = EntityState.Modified;
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
                var memberRow = db.members.Where(model => model.Id == id).FirstOrDefault();
                if (memberRow != null)
                {
                    db.Entry(memberRow).State = EntityState.Deleted;
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
            var memberRow = db.members.Where(model => model.Id == id).FirstOrDefault();
            return View(memberRow);
        }
    }
}