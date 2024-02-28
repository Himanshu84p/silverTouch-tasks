using OrderManagementAPI.DataAccess;
using OrderManagementAPI.Modals;

namespace OrderManagementAPI.Interfaces
{
    public interface ICustomer
    {
        //Getting all the Customer
        public Task<List<Customer>> GetAllCustomer();

        //getting the Customer by id 
        public Task<Customer> GetCustomerById(int id);

        //Adding the Customer
        public Task<CustomerModal> AddCustomer(CustomerModal customer);

        //Updating the Customer
        public Task<CustomerModal> UpdateCustomerById(int id,CustomerModal customer);

        //Deleting the Customer
        public Task<Customer> DeleteCustomerById(int id);
    }
}
