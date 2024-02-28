using Microsoft.EntityFrameworkCore;
using OrderManagementAPI.DataAccess;
using OrderManagementAPI.Interfaces;
using OrderManagementAPI.Modals;

namespace OrderManagementAPI.Repositories
{
    public class CustomerRepository : ICustomer
    {
        private readonly OrderManagementApiContext _orderManagementApiContext;

        public CustomerRepository(OrderManagementApiContext orderManagementApiContext) {
            
            _orderManagementApiContext = orderManagementApiContext;
        }

        //Adding the customer
        public async Task<CustomerModal> AddCustomer(CustomerModal customer)
        {
            Customer customer1 = new Customer()
            {
                CustomerId = 0,
                Name = customer.Name,
                Email = customer.Email,
                Address =  customer.Address,
                Password = customer.Password,
                Role = customer.Role,
                IsDeleted = false,
            };

            await _orderManagementApiContext.Customers.AddAsync(customer1);
            await _orderManagementApiContext.SaveChangesAsync();

            return customer;
        }

        //deleting the customer
        public async Task<Customer> DeleteCustomerById(int id)
        {
            var customer = await _orderManagementApiContext.Customers.FindAsync(id);

            if (customer != null)
            {
                customer.IsDeleted = true;
                await _orderManagementApiContext.SaveChangesAsync();
                return customer;
            }
            else
            {
                return null; // Customer not found
            }
        }


        //Getting the All customers
        public async Task<List<Customer>> GetAllCustomer()
        {
            var data = await _orderManagementApiContext.Customers.Where(ctm => ctm.IsDeleted == false).ToListAsync();
            return data;
        }

        //getting customer by id
        public async Task<Customer> GetCustomerById(int id)
        {
            var customer = await _orderManagementApiContext.Customers.FindAsync(id);
            return customer;
        }

        //updating the customer
        public async Task<CustomerModal> UpdateCustomerById(int id, CustomerModal customer)
        {
            Customer customer1 = new Customer()
            {
                CustomerId = customer.CustomerId,
                Name = customer.Name,
                Email = customer.Email,
                Address = customer.Address,
                Password = customer.Password,
                Role = customer.Role,
                IsDeleted = false,
            };
            _orderManagementApiContext.Entry(customer1).State = EntityState.Modified;
            await _orderManagementApiContext.SaveChangesAsync();

            return customer;
        }
    }
}
