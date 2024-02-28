using Microsoft.EntityFrameworkCore;
using OrderManagementAPI.DataAccess;
using OrderManagementAPI.Interfaces;
using OrderManagementAPI.Modals;

namespace OrderManagementAPI.Repositories
{
    public class ProductRepository : IProduct
    {
        private readonly OrderManagementApiContext _orderManagementApiContext;

        public ProductRepository(OrderManagementApiContext orderManagementApiContext)
        {

            _orderManagementApiContext = orderManagementApiContext;
        }

        //Adding the product
        public async Task<ProductModal> AddProduct(ProductModal product)
        {
            Product product1 = new Product()
            {
                ProductId = 0,
                Name = product.Name,
                Description = product.Description,
                Price = product.Price,
                StockQuantity = product.StockQuantity,
                IsDeleted = false,
            };

            await _orderManagementApiContext.Products.AddAsync(product1);
            await _orderManagementApiContext.SaveChangesAsync();

            return product;
        }

        public async Task<Product> DeleteProductById(int id)
        {
            var prod = await _orderManagementApiContext.Products.FindAsync(id);

            if (prod != null)
            {
                prod.IsDeleted = true;
                await _orderManagementApiContext.SaveChangesAsync();
                return prod;
            }
            else
            {
                return null; // Product not found
            }
        }

        public async Task<List<Product>> GetAllProduct()
        {
            var data = await _orderManagementApiContext.Products.Where(ctm => ctm.IsDeleted == false).ToListAsync();
            return data;
        }

        public async Task<Product> GetProductById(int id)
        {
            var prod = await _orderManagementApiContext.Products.FindAsync(id);
            return prod;
        }

        public async Task<ProductModal> UpdateProductById(int id, ProductModal product)
        {
            Product product1 = new Product()
            {
                ProductId = product.ProductId,
                Name = product.Name,
                Description = product.Description,
                Price = product.Price,
                StockQuantity = product.StockQuantity,
                IsDeleted = false,
            };
            _orderManagementApiContext.Entry(product1).State = EntityState.Modified;
            await _orderManagementApiContext.SaveChangesAsync();

            return product;

        }
    }
}
