using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using OrderManagementAPI.Interfaces;
using OrderManagementAPI.Modals;
using OrderManagementAPI.Repositories;

namespace OrderManagementAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CustomerController : ControllerBase
    {
        private readonly ICustomer icustomer;

        public CustomerController(ICustomer customer)
        {
            icustomer = customer;
        }

        //Getting all Customer controller
        [HttpGet]
        public async Task<IActionResult> GetAllCustomer() {

            var data = await icustomer.GetAllCustomer();

            return Ok(data);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetCustomerById(int id)
        {
            var customer1 = await icustomer.GetCustomerById(id);
            if (customer1 != null)
            {
                return Ok(customer1);
                
            } else
            {
                return NotFound();
            }
              
        }

        [HttpPost]
        public async Task<IActionResult> AddCustomer(CustomerModal ctm)
        { 
            if(ctm == null)
            {
                return null;
            } else
            {
                var customer1 = await icustomer.AddCustomer(ctm);
                return Ok(customer1);
            }
            
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> UpdateCustomerById(int id, CustomerModal ctm)
        {
            if (id == ctm.CustomerId)
            {
                await icustomer.UpdateCustomerById(id, ctm);
                return Ok(ctm);
            }
            else
            {
                return NotFound();
            }

        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCustomerById(int id)
        {
            if (id == null)
            {
                return NotFound();
            }
            else
            {
                var customer1 = await icustomer.DeleteCustomerById(id);
                return Ok(customer1);
            }

        }
    }
}
