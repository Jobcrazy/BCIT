using System.Globalization;
using CsvHelper;
using CsvHelper.Configuration;
using Microsoft.EntityFrameworkCore;
using Midterm.Models;

namespace Midterm.Data
{
    public class OlympicsContext : DbContext
    {
        public OlympicsContext(DbContextOptions<OlympicsContext> options)
        : base(options)
        {
        }

        public DbSet<Game>? Game { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            //Create Indexes
            modelBuilder.Entity<Game>().Property(g => g.Id)
                .ValueGeneratedOnAdd();
            modelBuilder.Entity<Game>().HasIndex(
                g => new { g.Id }).IsUnique(true);

            // Seed the data
            var _games = LoadData();
            modelBuilder.Entity<Game>().HasData(_games);
        }

        // Load Data from the CSV file
        private IEnumerable<Game> LoadData()
        {
            var csvFilePath = Path.Combine(Directory.GetCurrentDirectory(), "wwwroot/olympics.csv");

            var config = new CsvConfiguration(CultureInfo.InvariantCulture)
            {
                PrepareHeaderForMatch = args => args.Header.ToLower(),
            };

            var data = new List<Game>().AsEnumerable();
            using (var reader = new StreamReader(csvFilePath))
            {
                using (var csv = new CsvReader(reader, config))
                {
                    data = (csv.GetRecords<Game>()).ToList();
                }
            }

            return data;
        }
    }
}