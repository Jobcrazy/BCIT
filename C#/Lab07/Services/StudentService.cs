using System.Globalization;
using AspMongoApi.Models;
using CsvHelper;
using CsvHelper.Configuration;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace AspMongoApi.Services;

public class StudentsService
{
    private readonly IMongoCollection<Student> _studentsCollection;
    private readonly IEnumerable<Student> _students;

    public StudentsService(IOptions<StudentsDbSettings> studentsDatabaseSettings)
    {
        var mongoClient = new MongoClient(
            studentsDatabaseSettings.Value.ConnectionString);

        var mongoDatabase = mongoClient.GetDatabase(
            studentsDatabaseSettings.Value.DatabaseName);

        _studentsCollection = mongoDatabase.GetCollection<Student>(
            studentsDatabaseSettings.Value.CollectionName);

        _students = loadStudents();
    }

    public async Task<List<Student>> GetAsync() =>
        await _studentsCollection.Find(_ => true).ToListAsync();

    public async Task<Student?> GetAsync(string id) =>
        await _studentsCollection.Find(x => x.Id == id).FirstOrDefaultAsync();

    public async Task CreateAsync(Student newStudent) =>
        await _studentsCollection.InsertOneAsync(newStudent);

    public async Task UpdateAsync(string id, Student updatedStudent) =>
        await _studentsCollection.ReplaceOneAsync(x => x.Id == id, updatedStudent);

    public async Task RemoveAsync(string id) =>
        await _studentsCollection.DeleteOneAsync(x => x.Id == id);

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

    public async void InsertStudents()
    {
        foreach (var student in _students)
        {
            if (student.Id!.Length != 24)
            {
                student.Id = null;
                await _studentsCollection.InsertOneAsync(student);
            }
            else
            {
                var studentInDb = _studentsCollection.Find(
                    x=>x.Id == student.Id).FirstOrDefault();
                if (studentInDb == null)
                {
                    student.Id = null;
                    await _studentsCollection.InsertOneAsync(student);
                }
            }
        }

        var csvFilePath = Path.Combine(Directory.GetCurrentDirectory(), "wwwroot/students.csv");

        var config = new CsvConfiguration(CultureInfo.InvariantCulture)
        {
            PrepareHeaderForMatch = args => args.Header.ToLower(),
        };

        using (var writer = new StreamWriter(csvFilePath))
        {
            using (var csv = new CsvWriter(writer, config))
            {
                csv.WriteRecords(_students);
            }
        }
    }
}