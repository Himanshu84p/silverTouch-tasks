using BusinessAccessLayer_BAL_.Services;
using DataAccessLayer_DAL_.DataAccess;
using DataAccessLayer_DAL_.Interfaces;
using DataAccessLayer_DAL_.Repositories;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

//for accessing the DbContext 
var provider = builder.Services.BuildServiceProvider();
var config = provider.GetRequiredService<IConfiguration>();
builder.Services.AddDbContext<CoreScaffoldDbContext>(item => item.UseSqlServer(config.GetConnectionString("DefaultConnection")));
// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();



//to access repository and interface 
builder.Services.AddScoped<IUsers, UserRepository>();
builder.Services.AddScoped<IUserServices, UserService>();


var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
