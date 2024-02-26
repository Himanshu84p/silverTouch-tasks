using DataAccessLayer_DAL_.DataAccess;
using DataAccessLayer_DAL_.Interfaces;
using DataAccessLayer_DAL_.Modals;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessLayer_DAL_.Repositories
{
    public class UserRepository : IUsers
    {
        private readonly CoreScaffoldDbContext context;

        public UserRepository(CoreScaffoldDbContext context)
        {
            this.context = context;
        }

        //adding the user
        public async Task<UserModal> AddUser(UserModal user)
        {
            User newUser = new User()
            {
                Name = user.Name,
                Email = user.Email,
                Age = user.Age,
                Address = user.Address,
                Phone = user.Phone,
                Gender = user.Gender,
            };

            await context.Users.AddAsync(newUser);
            await context.SaveChangesAsync();

            return user;
        }

        //get all the user
        public async Task<List<User>> GetUsers()
        {
            return await context.Users.ToListAsync();
        }

        //get user by id
        public async Task<User> GetUserById(int id)
        {
            return await context.Users.FindAsync(id);
        }

        //update the user by id
        public async Task<UserModal> UpdateUser(int id, UserModal user)
        {

            User newUser = new User()
            {
                Id = user.Id,
                Name = user.Name,
                Email = user.Email,
                Age = user.Age,
                Address = user.Address,
                Phone = user.Phone,
                Gender = user.Gender,
            };
            context.Entry(newUser).State = EntityState.Modified;
            await context.SaveChangesAsync();
            return user;

        }

        //delete User
        public async Task<User> DeleteUser(int id)
        {
            var user = await context.Users.FindAsync(id);
            if (user != null)
            {
                context.Users.Remove(user);
                await context.SaveChangesAsync();
                return user;
            }
            else return null;
        }
    }
}
