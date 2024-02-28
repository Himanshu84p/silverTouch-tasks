using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using OrderManagementAPI.DataAccess;
using OrderManagementAPI.Interfaces;
using OrderManagementAPI.Modals;

namespace OrderManagementAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductController : ControllerBase
    {
        private readonly IProduct iproduct;

        public ProductController(IProduct product)
        {
            this.iproduct = product;
        }

        //Getting all product controller
        [HttpGet("GetAllProduct")]
        public async Task<IActionResult> GetAllProduct()
        {

            var data = await iproduct.GetAllProduct();

            return Ok(data);
        }

        [HttpGet("GetProductById/{id}")]
        public async Task<IActionResult> GetProductById(int id)
        {
            var product1 = await iproduct.GetProductById(id);
            if (product1 != null)
            {
                return Ok(product1);

            }
            else
            {
                return NotFound();
            }

        }

        [HttpPost("AddProduct")]
        public async Task<IActionResult> AddProduct(ProductModal prod)
        {
            if (prod == null)
            {
                return null;
            }
            else
            {
                var product1 = await iproduct.AddProduct(prod);
                return Ok(product1);
            }

        }

        [HttpPut("UpdateProductById/{id}")]
        public async Task<IActionResult> UpdateProductById(int id, ProductModal ctm)
        {
            if (id == ctm.ProductId)
            {
                await iproduct.UpdateProductById(id, ctm);
                return Ok(ctm);
            }
            else
            {
                return NotFound();
            }

        }

        [HttpDelete("DeleteProductById/{id}")]
        public async Task<IActionResult> DeleteProductById(int id)
        {
            if (id == null)
            {
                return NotFound();
            }
            else
            {
                var product1 = await iproduct.DeleteProductById(id);
                return Ok(product1);
            }

        }
    }
}
