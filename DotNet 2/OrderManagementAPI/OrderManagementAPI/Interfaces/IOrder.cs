using OrderManagementAPI.DataAccess;
using OrderManagementAPI.Modals;

namespace OrderManagementAPI.Interfaces
{
    public interface IOrder
    {
        //Getting all the Order
        public Task<List<Order>> GetAllOrder();

        //getting the Order by id 
        public Task<Order> GetOrderById(int id);

        //getting the Order by customerid 
        public Task<List<Order>> GetOrderByCustomerId(int id);

        //Adding the Order
        public Task<OrderModal> AddOrder(OrderModal order);

        //Updating the Order
        public Task<OrderModal> UpdateOrderById(int id, OrderModal order);

        //Deleting the Order
        public Task<Order> DeleteOrderById(int id);
    }
}
