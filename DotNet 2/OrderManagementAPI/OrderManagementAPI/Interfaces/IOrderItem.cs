using OrderManagementAPI.DataAccess;
using OrderManagementAPI.Modals;

namespace OrderManagementAPI.Interfaces
{
    public interface IOrderItem
    {
        //Getting all the OrderItem
        public Task<List<OrderItem>> GetAllOrderItem();

        //getting the OrderItem by id 
        public Task<OrderItem> GetOrderItemById(int id);

        //Adding the OrderItem
        public Task<OrderItemModal> AddOrderItem(OrderItemModal orderItem);

        //Updating the OrderItem
        public Task<OrderItemModal> UpdateOrderItemById(int id, OrderItemModal orderItem);

        //Deleting the OrderItem
        public Task<OrderItem> DeleteOrderItemById(int id);
    }
}
