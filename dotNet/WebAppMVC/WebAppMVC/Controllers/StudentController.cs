using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebAppMVC.Models;

namespace WebAppMVC.Controllers
{
    public class StudentController : Controller
    {
        // GET: Student
        public string getID(int id, string name = "null")
        {
            return "Student Profile!! \n id = " + id + " Name is " + name;
        }

        public ActionResult Index()
        {
            Student stu = new Student()
            {
                Id = 1,
                Name = "Rocky",
                Address = "Mumbai",
            };
            return View(stu);
        }

        public ViewResult AnotherView()
        {
            //Accessing the view of another view folder
            return View("~/Views/Home/Contact.cshtml");
        }

        
        public ActionResult About()
        {
            return View();  
        }

    }
}