﻿using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Text;

namespace OrderManagementUI.Controllers
{
    public class ProductController : Controller
    {
        private readonly HttpClient _httpClient;
        private readonly string _baseAddress;

        public ProductController(HttpClient httpClient)
        {
            _httpClient = httpClient;
            _baseAddress = "https://localhost:7216/api/Product";
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
                dynamic product = JObject.Parse(content);
                return View("Details", product);
            }
            return NotFound();
        }

        //Create View
        public IActionResult Create()
        {
            return View();
        }
        [HttpPost]
        public async Task<IActionResult> Create(IFormCollection productData)
        {
            var data = new Dictionary<string, string>();
            for (int i = 0; i < productData.Count; i++)
            {
                string field = productData.Keys.ElementAt(i);
                string value = productData[field];
                data.Add(field, value);

            }

            var jsonData = JsonConvert.SerializeObject(data);

            var content = new StringContent(jsonData, Encoding.UTF8, "application/json");
            var response = await _httpClient.PostAsync(_baseAddress, content);

            if (response.IsSuccessStatusCode)
            {
                TempData["SuccessMessage"] = "Product Created successfully.";
                return RedirectToAction("Index");
            }
            return View();
        }

        //Edit View

        public async Task<IActionResult> Edit(int id)
        {
            var response = await _httpClient.GetAsync(_baseAddress + $"/{id}");
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                dynamic product = JObject.Parse(content);

                return View("Edit", product);
            }
            return NotFound();
        }

        [HttpPost]
        public async Task<IActionResult> Edit(int id, Dictionary<string, string> formData)
        {


            var jsonData = JsonConvert.SerializeObject(formData);
            var content = new StringContent(jsonData, Encoding.UTF8, "application/json");
            var response = await _httpClient.PutAsync($"{_baseAddress}/{id}", content);

            if (response.IsSuccessStatusCode)
            {
                return RedirectToAction("Index", "Product");
            }
            else
            {
                return View("Edit", formData);
            }
        }

        public async Task<IActionResult> Delete(int id)
        {
            var response = await _httpClient.DeleteAsync(_baseAddress + $"/{id}");
            if (response.IsSuccessStatusCode)
            {
                TempData["success"] = "Product Deleted Successfully";
                return RedirectToAction("Index");
            }
            return NotFound();
        }
    }
}