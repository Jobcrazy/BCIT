using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddDbContext<SchoolDbContext>(
  option => option.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));

// Add Cors
builder.Services.AddCors(o => o.AddPolicy("Policy", builder =>
{
    builder.AllowAnyOrigin()
    .AllowAnyMethod()
    .AllowAnyHeader();
}));

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

//app.UseHttpsRedirection();

//var ssvr = app.Services.GetService<StudentService>();
//ssvr!.InsertStudents();

app.UseCors("Policy");

app.UseAuthorization();

app.MapControllers();

app.Run();
