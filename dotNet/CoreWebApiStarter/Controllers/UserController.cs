using CoreWebApiStarter.DataAccess;
using CoreWebApiStarter.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace CoreWebApiStarter.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly IUsers users;

        public UserController(IUsers users)
        {
            this.users = users;
        }

        [HttpGet("GetAllUsers")]
        public async Task<IActionResult> GetUsers()
        {
            var data = await users.GetUsers();
            return Ok(data);
        }

        [HttpPost("CreateUser")]
        public async Task<IActionResult> CreateUser(User user)
        {
            if (user == null)
            {
                return NotFound();
            }
            else
            {
                var data = await users.AddUser(user);
                return Ok(data);
            }
        }

        [HttpGet("GetUserById/{id}")]
        public async Task<IActionResult> GetUserByID(int id)
        {
            var user = await users.GetUserById(id);
            return Ok(user);
        }

        [HttpPut("UpdateUserById/{id}")]
        public async Task<IActionResult> UpdateUserById(int id, User user)
        {
            if (id != user.Id) { 
                return NotFound();
            }
            else
            {
                await users.UpdateUser(id, user);
                return Ok(user);
            }
        }

        [HttpDelete("DeleteUserById/{id}")]
        public async Task<IActionResult> DeleteUserById(int id)
        {
            if (id == null)
            {
                return NotFound();
            }
            else
            {
                var deletedUser = await users.DeleteUser(id);
                return Ok(deletedUser);
            }
        }
    }
}
