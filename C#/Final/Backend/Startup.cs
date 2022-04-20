using System;
using AzureFunc.Data;
using AzureFunc.Models;
using Microsoft.Azure.Functions.Extensions.DependencyInjection;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;

[assembly: FunctionsStartup(typeof(AzureFunc.StartUp))]
namespace AzureFunc
{
    public class StartUp : FunctionsStartup
    {
        public override void Configure(IFunctionsHostBuilder builder)
        {
            /*builder.Services.AddDbContext<NhlContext>(options =>
                options.UseSqlServer(Helper.GetSetting("SQL-SERVER_DATABASE_CONNECTION_STRING")));
            */

            //*
            // If it's MySQL
            var serverVersion = new MySqlServerVersion(new Version(8, 0, 0));
            var connectionString = Environment.GetEnvironmentVariable("MYSQL_DATABASE_CONNECTION_STRING");
            builder.Services.AddDbContext<NhlContext>(option => option.UseMySql(connectionString, serverVersion));
            //*/
        }
    }
}
