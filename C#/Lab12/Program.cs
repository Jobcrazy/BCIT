using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Code1st.Data;
using System.Reflection;
using Code1st;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllersWithViews()
.AddDataAnnotationsLocalization(
    option =>
    {
        option.DataAnnotationLocalizerProvider = (type, factory) =>
        {
            var asmName = new AssemblyName(typeof(AnnoData).GetTypeInfo().Assembly.FullName!);
            return factory.Create("DataAnno", asmName.Name!);
        };
    }
)
.AddViewLocalization();

builder.Services.AddLocalization(options => options.ResourcesPath = "Resources");

// Add services to the container.
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");
builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseSqlite(connectionString));
builder.Services.AddDatabaseDeveloperPageExceptionFilter();

builder.Services.AddDefaultIdentity<IdentityUser>(options => options.SignIn.RequireConfirmedAccount = true)
    .AddEntityFrameworkStores<ApplicationDbContext>();

builder.Services.AddControllers().AddNewtonsoftJson(options =>
  options.SerializerSettings.ReferenceLoopHandling = Newtonsoft.Json.ReferenceLoopHandling.Ignore
);

var app = builder.Build();

var supportedCultures = new[] {
    "en","en-US","en-CA","fr","fr-FR","zh-CN","ar","ar-EG","ko-KR","ru-RU"
};

var localizationOptions = new RequestLocalizationOptions().SetDefaultCulture(supportedCultures[1])
    .AddSupportedCultures(supportedCultures)
    .AddSupportedUICultures(supportedCultures);

localizationOptions.ApplyCurrentCultureToResponseHeaders = true;

app.UseRequestLocalization(localizationOptions);

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseMigrationsEndPoint();
}
else
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

//app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthentication();
app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");
app.MapRazorPages();

app.Run();
