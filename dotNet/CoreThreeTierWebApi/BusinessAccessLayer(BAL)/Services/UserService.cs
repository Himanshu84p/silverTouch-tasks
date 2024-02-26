using DataAccessLayer_DAL_.DataAccess;
using DataAccessLayer_DAL_.Interfaces;
using DataAccessLayer_DAL_.Modals;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BusinessAccessLayer_BAL_.Services
{
    public class UserService : IUserServices
    {
        private readonly IUsers _services;

        public UserService(IUsers services)
        {
            _services = services;
        }

        public Task<UserModal> AddUser(UserModal user)
        {
            return _services.AddUser(user);
        }

        public Task<User> DeleteUser(int id)
        {
            return _services.DeleteUser(id);
        }

        public Task<User> GetUserById(int id)
        {
            return _services.GetUserById(id);
        }

        public Task<List<User>> GetUsers()
        {
            return _services.GetUsers();
        }

        public Task<UserModal> UpdateUser(int id, UserModal user)
        {
            return _services.UpdateUser(id, user);
        }
    }
}
