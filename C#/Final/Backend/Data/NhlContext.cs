using System;
using System.Collections.Generic;
using AzureFunc.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.Extensions.Configuration;

namespace AzureFunc.Data
{
    public partial class NhlContext : DbContext
    {
        public NhlContext() { }

        public NhlContext(DbContextOptions<NhlContext> options) : base(options) { }

        public NhlContext(DbContextOptions options) : base(options) { }


        public virtual DbSet<Player> Players { get; set; }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);

            builder.Entity<Player>(entity =>
            {
                entity.Property(e => e.FirstName).IsRequired();
                entity.Property(e => e.LastName).IsRequired();
                entity.ToTable("Player");
            });

            builder.Entity<Player>().HasData(Helper.GetPlayers());
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                var serverVersion = new MySqlServerVersion(new Version(8, 0, 0));
                optionsBuilder.UseMySql(
                    Helper.GetSetting("MYSQL_DATABASE_CONNECTION_STRING"),
                    serverVersion                    
                );
            }
        }
    }
}
