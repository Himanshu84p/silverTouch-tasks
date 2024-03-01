using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Identity.Client;
using OrderManagementAPI.DataAccess;
using OrderManagementAPI.Interfaces;
using OrderManagementAPI.Modals;

namespace OrderManagementAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OrderController : ControllerBase
    {
        private readonly IOrder iorder;

        public OrderController(IOrder order)
        {
           iorder = order;
        }


        //Getting all Order controller
        [HttpGet]
        public async Task<IActionResult> GetAllOrder()
        {

            var data = await iorder.GetAllOrder();

            return Ok(data);
        }

        //getting the order by customerId
        [HttpGet("GetOrderByCustomerId/{id}")]
        public async Task<IActionResult> GetOrderByCustomerId(int id)
        {
            var custOrders = await iorder.GetOrderByCustomerId(id);
            if (custOrders != null)
            {
                return Ok(custOrders);

            }
            else
            {
                return NotFound();
            }

        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetOrderById(int id)
        {
            var order1 = await iorder.GetOrderById(id);
            if (order1 != null)
            {
                return Ok(order1);

            }
            else
            {
                return NotFound();
            }

        }

        [HttpPost("AddOrder")]
        public async Task<IActionResult> AddOrder(OrderModal od)
        {
            if (od == null)
            {
                return null;
            }
            else
            {
                var order1 = await iorder.AddOrder(od);
                return Ok(order1);
            }

        }

        [HttpPut("{id}")]
        public async Task<IActionResult> UpdateOrderById(int id, OrderModal od)
        {
            if (id == od.OrderId)
            {
                await iorder.UpdateOrderById(id, od);
                return Ok(od);
            }
            else
            {
                return NotFound();
            }

        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteOrderById(int id)
        {
            if (id == null)
            {
                return NotFound();
            }
            else
            {
                var order1 = await iorder.DeleteOrderById(id);
                return Ok(order1);
            }

        }
    }
}
