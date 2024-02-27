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
        private readonly ILogger<UserController> logger;

        public UserController(IUserServices users, ILogger<UserController> logger)
        {
            this.users = users;
            this.logger = logger;
        }

        [HttpGet("GetAllUsers")]
        public async Task<IActionResult> GetUsers()
        {
            logger.LogInformation("Gettting all users");
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
                logger.LogInformation("Create new User");
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
                logger.LogInformation("Update the User");
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
                logger.LogInformation("delete new User");
                var deletedUser = await users.DeleteUser(id);
                return Ok(deletedUser);
            }
        }
    }
}
