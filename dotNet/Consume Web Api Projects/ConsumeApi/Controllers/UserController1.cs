using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json;
using System.Text;

namespace ConsumeApi.Controllers
{
    public class UserController1 : Controller
    {
        private readonly HttpClient _httpClient;

        public UserController1()
        {
            _httpClient = new HttpClient();
            
        }

        public async Task<IActionResult> Index()
        {
            // Call your API endpoint to fetch data
            HttpResponseMessage response = await _httpClient.GetAsync("http://192.168.11.99:8082/api/User/GetAllUsers");

            if (response.IsSuccessStatusCode)
            {
                // Deserialize the JSON response directly
                var responseData = await response.Content.ReadAsStringAsync();

                // Pass the JSON data to the view
                return View("Index", responseData);
            }
            else
            {
                // Handle error
                return View("Error");
            }
        }

        // GET: UserController1/Create
        public IActionResult Create()
        {
            return View();
        }
        // POST: UserController1/Create
        [HttpPost]
        public async Task<IActionResult> Create(IFormCollection formData)
        {
            var data = new Dictionary<string, string>();

            for (int i = 0; i < formData.Count; i++)
            {
                string field = formData.Keys.ElementAt(i);
                string value = formData[field];
                data.Add(field, value);
            }

            var jsonData = JsonConvert.SerializeObject(data);

            var httpClient = new HttpClient();
            var apiUrl = "http://192.168.11.99:8082/api/User/CreateUser";

            var content = new StringContent(jsonData, Encoding.UTF8, "application/json");

            var response = await httpClient.PostAsync(apiUrl, content);
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction("Index");
            }
            return RedirectToAction("Index");
        }

        //editing the user 
        public async Task<IActionResult> Edit(int id)
        {
            var httpClient = new HttpClient();
            var apiUrl = $"http://192.168.11.99:8082/api/User/GetUserById/{id}";

            var response = await httpClient.GetAsync(apiUrl);
            if (response.IsSuccessStatusCode)
            {
                var userData = await response.Content.ReadAsStringAsync();
                ViewBag.UserId = id; // Pass the user ID to the view
                
                return View("Edit", userData);
            }
            else
            {
                // Handle error
                return View("Error");
            }
        }

        [HttpPost]
        public async Task<IActionResult> Edit(IFormCollection formData)
        {
            var id = formData["Id"]; // Get the user ID from the form data

            // Prepare the updated user data
            var updatedUserData = new Dictionary<string, string>();
            for (int i = 0; i < formData.Count; i++)
            {
                string field = formData.Keys.ElementAt(i);
                string value = formData[field];
                updatedUserData.Add(field, value);
            }
            var jsonData = JsonConvert.SerializeObject(updatedUserData);

            var httpClient = new HttpClient();
            var apiUrl = $"http://192.168.11.99:8082/api/User/UpdateUserById/{id}";

            var content = new StringContent(jsonData, Encoding.UTF8, "application/json");

            var response = await httpClient.PutAsync(apiUrl, content);
            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction("Index");
            }
            else
            {
                // Handle error
                return View("Error");
            }
        }

        //details of the user
        public async Task<IActionResult> Details(int id)
        {
            var httpClient = new HttpClient();
            var apiUrl = $"http://192.168.11.99:8082/api/User/GetUserById/{id}";

            var response = await httpClient.GetAsync(apiUrl);
            if (response.IsSuccessStatusCode)
            {
                var userData = await response.Content.ReadAsStringAsync();
                ViewBag.UserId = id; // Pass the user ID to the view
                
                return View("Details", userData);
            }
            else
            {
                // Handle error
                return View("Error");
            }
        }


 

        // GET: UserController1/Delete/5
        public IActionResult DeleteConfirm(int id)
        {
            ViewBag.UserId = id; // Pass the user ID to the view
            return View("Delete");
        }

        // POST: UserController1/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Delete(int id)
        {
            HttpResponseMessage response = await _httpClient.DeleteAsync($"http://192.168.11.99:8082/api/User/DeleteUserById/{id}");
            if (response.IsSuccessStatusCode)
            {
                // Redirect to Index action after successful deletion
                return RedirectToAction("Index");
            }
            else
            {
                return View("Error");
            }
        }
    }

    
}
