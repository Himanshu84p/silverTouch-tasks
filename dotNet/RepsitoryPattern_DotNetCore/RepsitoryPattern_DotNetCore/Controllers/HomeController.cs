using Microsoft.AspNetCore.Mvc;
using RepsitoryPattern_DotNetCore.DataAccess;
using RepsitoryPattern_DotNetCore.Interfaces;
using RepsitoryPattern_DotNetCore.Models;
using System.Diagnostics;

namespace RepsitoryPattern_DotNetCore.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;
        private readonly IEmployee iemployee;

        public HomeController(ILogger<HomeController> logger, IEmployee _employee)
        {
            _logger = logger;
            this.iemployee = _employee;
        }

        public IActionResult Index()
        {
            var data = iemployee.GetEmployees();
            return View(data);
        }

        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Create(Employee emp)
        {
            if (emp != null)
            {
                iemployee.Create(emp);
                
            }
            return RedirectToAction("Index", "Home");
        }
        public IActionResult Edit(int id)
        {
            var emp = iemployee.GetEmp(id);
            return View(emp);
        }

        [HttpPost]
        public IActionResult Edit(Employee employee)
        {
            if (employee != null)
            {
                iemployee.Edit(employee);
                return RedirectToAction("Index");
            } else
            {
                return NotFound();
            }
            
        }

        public IActionResult Delete(int id)
        {
            var emp = iemployee.GetEmp(id);
            return View(emp);
        }

        [HttpPost]
        public IActionResult Delete(Employee employee)
        {
            if (employee != null)
            {
                iemployee.Delete(employee);
                return RedirectToAction("Index");
            }
            else
            {
                return NotFound();
            }

        }

        public IActionResult Details(int id)
        {
            var emp = iemployee.GetEmp(id);
            return View(emp);
        }


        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
