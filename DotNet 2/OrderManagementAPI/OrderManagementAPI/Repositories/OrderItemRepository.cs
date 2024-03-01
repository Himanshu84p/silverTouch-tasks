using Microsoft.EntityFrameworkCore;
using OrderManagementAPI.DataAccess;
using OrderManagementAPI.Interfaces;
using OrderManagementAPI.Modals;

namespace OrderManagementAPI.Repositories
{
    public class OrderItemRepository : IOrderItem

    {

        private readonly OrderManagementApiContext _orderManagementApiContext;

        public OrderItemRepository(OrderManagementApiContext orderManagementApiContext)
        {

            _orderManagementApiContext = orderManagementApiContext;
        }

        //adding the item
        public async Task<OrderItemModal> AddOrderItem(OrderItemModal orderItem)
        {
            OrderItem item = new OrderItem()
            {
                OrderItemId = 0,
                OrderId = orderItem.OrderId,
                ProductId = orderItem.ProductId,
                Quantity = orderItem.Quantity,
                UnitPrice = orderItem.UnitPrice,
                IsDeleted = false,
            };
            await _orderManagementApiContext.OrderItems.AddAsync(item);
            await _orderManagementApiContext.SaveChangesAsync();

            return orderItem;
        }

        public async Task<OrderItem> DeleteOrderItemById(int id)
        {
            var orderItem = await _orderManagementApiContext.OrderItems.FindAsync(id);

            if (orderItem != null)
            {
                orderItem.IsDeleted = true;
                await _orderManagementApiContext.SaveChangesAsync();
                return orderItem;
            }
            else
            {
                return null; // Customer not found
            }
        }

        public async Task<List<OrderItem>> GetAllOrderItem()
        {
            var data = await _orderManagementApiContext.OrderItems.Where(oditem => oditem.IsDeleted == false).ToListAsync();
            return data;
        }

        public async Task<List<OrderItem>> GetItemByOrderId(int id)
        {
            var data = await _orderManagementApiContext.OrderItems.Where(oditem => oditem.IsDeleted == false && oditem.OrderId == id).ToListAsync();
            return data;
        }

        public async Task<OrderItem> GetOrderItemById(int id)
        {
            var orderItem = await _orderManagementApiContext.OrderItems.FindAsync(id);
            return orderItem;
        }

        public async Task<OrderItemModal> UpdateOrderItemById(int id, OrderItemModal orderItem)
        {
            OrderItem item = new OrderItem()
            {
                OrderItemId = orderItem.OrderItemId,
                OrderId = orderItem.OrderId,
                ProductId = orderItem.ProductId,
                Quantity = orderItem.Quantity,
                UnitPrice = orderItem.UnitPrice,
                IsDeleted = false,
            };
            _orderManagementApiContext.Entry(item).State = EntityState.Modified;
            await _orderManagementApiContext.SaveChangesAsync();

            return orderItem;
        }
    }
}
