var builder = WebApplication.CreateBuilder(args);

// Add services to the container. without this line view will not reflect to the browser.
builder.Services.AddControllersWithViews();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
}
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

// app.MapDefaultControllerRoute(); // this line run the default route if we don't specify 

//this is for custom routing
app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Privacy}/{id?}");

app.Run();
