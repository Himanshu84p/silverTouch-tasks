using OrderManagementAPI.DataAccess;
using OrderManagementAPI.Modals;

namespace OrderManagementAPI.Interfaces
{
    public interface IProduct
    {
        //Getting all the Product
        public Task<List<Product>> GetAllProduct();

        //getting the Product by id 
        public Task<Product> GetProductById(int id);

        //Adding the Product
        public Task<ProductModal> AddProduct(ProductModal product);

        //Updating the Product
        public Task<ProductModal> UpdateProductById(int id, ProductModal product);

        //Deleting the Product
        public Task<Product> DeleteProductById(int id);
    }
}
