using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading.Tasks;
using CsvHelper;
using CsvHelper.Configuration;
using Microsoft.EntityFrameworkCore;
using StudentsLibrary;

public class SchoolDbContext : DbContext
{
    public DbSet<Student>? Students { get; set; }
    public SchoolDbContext(DbContextOptions<SchoolDbContext> options) : base(options) { }

    private IEnumerable<Student> loadStudents()
    {
        var csvFilePath = Path.Combine(Directory.GetCurrentDirectory(), "wwwroot/students.csv");

        var config = new CsvConfiguration(CultureInfo.InvariantCulture)
        {
            PrepareHeaderForMatch = args => args.Header.ToLower(),
        };

        var data = new List<Student>().AsEnumerable();
        using (var reader = new StreamReader(csvFilePath))
        {
            using (var csv = new CsvReader(reader, config))
            {
                data = (csv.GetRecords<Student>()).ToList();
            }
        }

        return data;
    }

    protected override void OnModelCreating(ModelBuilder builder)
    {
        base.OnModelCreating(builder);

        var _students = loadStudents();

        builder.Entity<Student>().HasData(_students);
    }
}
