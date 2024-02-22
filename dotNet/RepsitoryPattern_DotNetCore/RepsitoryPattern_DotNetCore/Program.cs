using Microsoft.EntityFrameworkCore;
using RepsitoryPattern_DotNetCore.DataAccess;
using RepsitoryPattern_DotNetCore.Interfaces;
using RepsitoryPattern_DotNetCore.Repositories;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();

//for accessing the DbContext 
var provider = builder.Services.BuildServiceProvider();
var config = provider.GetRequiredService<IConfiguration>();
builder.Services.AddDbContext<CoreScaffoldDbContext>(item => item.UseSqlServer(config.GetConnectionString("DefaultConnection")));

//to access repository and interface 
builder.Services.AddScoped<IEmployee, EmployeeRepository>();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.Run();
