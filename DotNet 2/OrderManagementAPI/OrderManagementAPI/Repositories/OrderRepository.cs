using Microsoft.EntityFrameworkCore;
using OrderManagementAPI.DataAccess;
using OrderManagementAPI.Interfaces;
using OrderManagementAPI.Modals;

namespace OrderManagementAPI.Repositories
{
    public class OrderRepository : IOrder
    {
        private readonly OrderManagementApiContext _orderManagementApiContext;

        public OrderRepository(OrderManagementApiContext orderManagementApiContext)
        {

            _orderManagementApiContext = orderManagementApiContext;
        }

        //Adding the Order
        public async Task<OrderModal> AddOrder(OrderModal order)
        {
            Order order1 = new Order()
            {
                OrderId = 0,
                CustomerId = order.CustomerId,
                OrderDate = DateTime.Now,
                TotalAmount = order.TotalAmount,
                Status = order.Status,
                IsDeleted = false,
                
            };

            await _orderManagementApiContext.Orders.AddAsync(order1);
            await _orderManagementApiContext.SaveChangesAsync();

            return order;
        }

        //deleting the user
        public async Task<Order> DeleteOrderById(int id)
        {
            var order = await _orderManagementApiContext.Orders.FindAsync(id);

            if (order != null)
            {
                order.IsDeleted = true;
                await _orderManagementApiContext.SaveChangesAsync();
                return order;
            }
            else
            {
                return null; // Customer not found
            }
        }

        //Getting all the order
        public async Task<List<Order>> GetAllOrder()
        {
            var data = await _orderManagementApiContext.Orders.Where(od => od.IsDeleted == false).ToListAsync();
            return data;
        }

        //getting orders by customerId
        public async Task<List<Order>> GetOrderByCustomerId(int id)
        {
            var custOrders = await _orderManagementApiContext.Orders.Where(o => o.CustomerId == id && o.IsDeleted == false).ToListAsync();
            return custOrders;
        }

        public async Task<Order> GetOrderById(int id)
        {
            var order = await _orderManagementApiContext.Orders.FindAsync(id);
            return order;
        }

        //Updating the order
        public async Task<OrderModal> UpdateOrderById(int id, OrderModal order)
        {
            Order order1 = new Order()
            {
                OrderId = order.OrderId,
                CustomerId = order.CustomerId,
                OrderDate = DateTime.Now,
                TotalAmount = order.TotalAmount,
                Status = order.Status,
                IsDeleted = false,

            };

            _orderManagementApiContext.Entry(order1).State = EntityState.Modified;
            await _orderManagementApiContext.SaveChangesAsync();

            return order;
        }
    }
}
