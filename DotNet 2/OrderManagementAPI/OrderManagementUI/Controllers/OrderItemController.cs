using Microsoft.AspNetCore.Mvc;
using System.Net.Http;
using Newtonsoft.Json;
using System.Text;
using Microsoft.AspNetCore.Mvc.Infrastructure;

namespace OrderManagementUI.Controllers
{
    public class OrderItemController : Controller
    {

        private readonly HttpClient _httpClient;
        private readonly string _baseAddress;
        private readonly string _productAddress;

        public OrderItemController(HttpClient httpClient)
        {
            _httpClient = httpClient;
            _baseAddress = "https://localhost:7216/api/OrderItem";
            _productAddress = "https://localhost:7216/api/Product";
        }
        public IActionResult Index()
        {
            return View();
        }

        //Adding the products to the order
        public async Task<IActionResult> AddProduct(int id)
        {
            TempData["OrderId"] = id;
            var response = await _httpClient.GetAsync(_productAddress);
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                return View("AddProduct", content);
            }
            return NotFound();

        }

        [HttpPost]
        public async Task<IActionResult> AddProductToOrder(int orderId, IFormCollection orderData)
        {
            var data = new Dictionary<string, string>();
            

            for (int i = 0; i < orderData.Count; i++)
            {
                string field = orderData.Keys.ElementAt(i);
                string value = orderData[field];
                data.Add(field, value);

            }

            var jsonData = JsonConvert.SerializeObject(data);

            var content = new StringContent(jsonData, Encoding.UTF8, "application/json");
            var response = await _httpClient.PostAsync(_baseAddress , content);

            if (response.IsSuccessStatusCode)
            {
                TempData["SuccessMessage"] = "Order Created successfully.";
                return RedirectToAction("Index", "Customer");
            }
            return View("AddProduct",orderId);
        }

        public async Task<IActionResult> ViewProduct(int id)
        {
            var response = await _httpClient.GetAsync(_baseAddress + $"/GetOrderItemById/{id}");
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                return View("ViewProduct", content);
            }
            return NotFound();
            
        }
    }
}
