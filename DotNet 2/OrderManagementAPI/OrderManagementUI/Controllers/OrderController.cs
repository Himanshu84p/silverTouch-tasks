using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Text;

namespace OrderManagementUI.Controllers
{
    public class OrderController : Controller
    {
        private readonly HttpClient _httpClient;
        private readonly string _baseAddress;

        public OrderController(HttpClient httpClient)
        {
            _httpClient = httpClient;
            _baseAddress = "https://localhost:7216/api/Order";
        }
        //index view for particular customer
        public async Task<IActionResult> Index(int id)
        {
            var response = await _httpClient.GetAsync(_baseAddress + $"/GetOrderByCustomerId/{id}");
            if (response.IsSuccessStatusCode)
            {
                var custOrders = await response.Content.ReadAsStringAsync();
                TempData["customerId"] = id;

                return View("Index", custOrders);
            }
            return NotFound();
        }

        //return create view
        public IActionResult Create(int id)
        {
            
            return View(id);
        }

        [HttpPost]
        public async Task<IActionResult> Create(int customerId, IFormCollection orderData)
        {
            var data = new Dictionary<string, string>();
            data.Add("orderId", "0");

            for (int i = 0; i < orderData.Count ; i++)
            {
                string field = orderData.Keys.ElementAt(i);
                string value = orderData[field];
                data.Add(field, value);

            }

            var jsonData = JsonConvert.SerializeObject(data);

            var content = new StringContent(jsonData, Encoding.UTF8, "application/json");
            var response = await _httpClient.PostAsync(_baseAddress + "/AddOrder", content);

            if (response.IsSuccessStatusCode)
            {
                TempData["SuccessMessage"] = "Order Created successfully.";
                return RedirectToAction("Index",new { id = customerId });
            }
            return View();
        }

        //deleting  the order
        public async Task<IActionResult> Delete(int id, int customerId)
        {
            var response = await _httpClient.DeleteAsync(_baseAddress + $"/{id}");
            if (response.IsSuccessStatusCode)
            {
                TempData["success"] = "Order Deleted Successfully";
                return RedirectToAction("Index", new {id = customerId });
            }
            return NotFound();
        }

        
        
    }
}
