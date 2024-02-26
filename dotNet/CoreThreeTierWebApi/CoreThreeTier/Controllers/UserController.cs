using BusinessAccessLayer_BAL_.Services;
using DataAccessLayer_DAL_.DataAccess;
using DataAccessLayer_DAL_.Interfaces;
using DataAccessLayer_DAL_.Modals;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace CoreThreeTier.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly IUserServices users;

        public UserController(IUserServices users)
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
        public async Task<IActionResult> CreateUser(UserModal user)
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
        public async Task<IActionResult> UpdateUserById(int id, UserModal user)
        {

            if (id == user.Id)
            {
                await users.UpdateUser(id, user);
                return Ok(user);            }
            else
            {
                return NotFound();
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
