using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Text;

namespace OrderManagementUI.Controllers
{
    public class CustomerController : Controller
    {
        private readonly HttpClient _httpClient;
        private readonly string _baseAddress;

        public CustomerController(HttpClient httpClient)
        {
            _httpClient = httpClient;
            _baseAddress = "https://localhost:7216/api/Customer";
        }

        //index view
        public async Task<IActionResult> Index()
        {

            var response = await _httpClient.GetAsync(_baseAddress);
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                return View("Index", content);
            }
            return NotFound();
        }

        //Details View
        public async Task<IActionResult> Details(int id)
        {
            var response = await _httpClient.GetAsync(_baseAddress + $"/{id}");
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                dynamic student = JObject.Parse(content);
                return View("Details",student);
            }
            return NotFound();
        }

        //Create View
        public IActionResult Create()
        {
            return View();
        }
        [HttpPost]
        public async Task<IActionResult> Create(IFormCollection customerData)
        {
            var data = new Dictionary<string, string>();
            for (int i = 0; i < customerData.Count; i++)
            {
                string field = customerData.Keys.ElementAt(i);
                string value = customerData[field];
                data.Add(field, value);

            }

            var jsonData = JsonConvert.SerializeObject(data);

            var content = new StringContent(jsonData, Encoding.UTF8, "application/json");
            var response = await _httpClient.PostAsync(_baseAddress, content);

            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction("Index");
            }
            return View();
        }

        

        
    }
}
