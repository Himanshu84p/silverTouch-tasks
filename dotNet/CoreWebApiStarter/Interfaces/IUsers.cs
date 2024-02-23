
using CoreWebApiStarter.DataAccess;
using Microsoft.AspNetCore.Mvc;

namespace CoreWebApiStarter.Interfaces
{
    public interface IUsers
    {
        //to Get all the users
        public Task<List<User>> GetUsers();

        //add user
        public Task<User> AddUser(User user);

        //get user by id
        public Task<User> GetUserById(int id);

        //edit user
        public Task<User> UpdateUser(int id , User user);

        //delete user
        public Task<User> DeleteUser(int id);

    }
}
