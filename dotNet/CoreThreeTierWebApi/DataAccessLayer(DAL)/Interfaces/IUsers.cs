using DataAccessLayer_DAL_.DataAccess;
using DataAccessLayer_DAL_.Modals;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessLayer_DAL_.Interfaces
{
    public interface IUsers
    {
        //to Get all the users
        public Task<List<User>> GetUsers();

        //add user
        public Task<UserModal> AddUser(UserModal user);

        //get user by id
        public Task<User> GetUserById(int id);

        //edit user
        public Task<UserModal> UpdateUser(int id, UserModal user);

        //delete user
        public Task<User> DeleteUser(int id);
    }
}
