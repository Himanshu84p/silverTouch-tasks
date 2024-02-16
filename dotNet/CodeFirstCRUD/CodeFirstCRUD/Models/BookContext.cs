using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;
using System.Security.Policy;

namespace CodeFirstCRUD.Models
{
    public class BookContext : DbContext
    {
        public DbSet<Book> books {  get; set; }

        public DbSet<Member> members { get; set; }
        public DbSet<Publishers> publishers { get; set; }
    }
}