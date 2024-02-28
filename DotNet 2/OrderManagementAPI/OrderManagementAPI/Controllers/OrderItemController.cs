using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using OrderManagementAPI.DataAccess;
using OrderManagementAPI.Interfaces;
using OrderManagementAPI.Modals;

namespace OrderManagementAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OrderItemController : ControllerBase
    {
        private readonly IOrderItem iorderitem;

        public OrderItemController(IOrderItem iorderitem)
        {
            this.iorderitem = iorderitem;
        }

        //Getting all OrderItem controller
        [HttpGet("GetAllOrderItem")]
        public async Task<IActionResult> GetAllOrderItem()
        {

            var data = await iorderitem.GetAllOrderItem();

            return Ok(data);
        }

        [HttpGet("GetOrderItemById/{id}")]
        public async Task<IActionResult> GetOrderItemById(int id)
        {
            var orderItem1 = await iorderitem.GetOrderItemById(id);
            if (orderItem1 != null)
            {
                return Ok(orderItem1);

            }
            else
            {
                return NotFound();
            }

        }

        [HttpPost("AddOrderItem")]
        public async Task<IActionResult> AddOrderItem(OrderItemModal odItem)
        {
            if (odItem == null)
            {
                return null;
            }
            else
            {
                var orderItem1 = await iorderitem.AddOrderItem(odItem);
                return Ok(orderItem1);
            }

        }

        [HttpPut("UpdateOrderItemById/{id}")]
        public async Task<IActionResult> UpdateOrderItemById(int id, OrderItemModal odItem)
        {
            if (id == odItem.OrderItemId)
            {
                await iorderitem.UpdateOrderItemById(id, odItem);
                return Ok(odItem);
            }
            else
            {
                return NotFound();
            }

        }

        [HttpDelete("DeleteOrderItemById/{id}")]
        public async Task<IActionResult> DeleteOrderItemById(int id)
        {
            if (id == null)
            {
                return NotFound();
            }
            else
            {
                var orderItem1 = await iorderitem.DeleteOrderItemById(id);
                return Ok(orderItem1);
            }

        }
    }
}
