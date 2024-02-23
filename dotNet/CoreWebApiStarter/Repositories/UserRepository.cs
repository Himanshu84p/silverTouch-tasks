using CoreWebApiStarter.DataAccess;
using CoreWebApiStarter.Interfaces;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace CoreWebApiStarter.Repositories
{
    public class UserRepository : IUsers
    {
        private readonly CoreScaffoldDbContext context;

        public UserRepository(CoreScaffoldDbContext context)
        {
            this.context = context;
        }

        //adding the user
        public async Task<User> AddUser(User user)
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

            return newUser;
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
        public async Task<User> UpdateUser(int id, User user)
        {
            context.Entry(user).State = EntityState.Modified;
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
