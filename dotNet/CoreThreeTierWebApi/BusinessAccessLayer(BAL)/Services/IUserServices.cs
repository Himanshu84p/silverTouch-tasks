using DataAccessLayer_DAL_.DataAccess;
using DataAccessLayer_DAL_.Modals;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BusinessAccessLayer_BAL_.Services
{
    public interface IUserServices

    {
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
