#nullable disable
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using StudentsLibrary;
using CsvHelper.Configuration;
using System.Globalization;
using CsvHelper;
using Google.DataTable.Net.Wrapper;

namespace SchoolApi.Controllers;

[EnableCors("Policy")]
[Route("api/[controller]")]
[ApiController]
public class StudentsController : ControllerBase
{
    private readonly SchoolDbContext _context;

    public StudentsController(SchoolDbContext context)
    {
        _context = context;
    }

    // GET: api/Students
    [HttpGet]
    public async Task<ActionResult<IEnumerable<Student>>> GetStudents()
    {
        return await _context.Students.ToListAsync();
    }

    // GET: api/Students/5
    [HttpGet("{id}")]
    public async Task<ActionResult<Student>> GetStudent(string id)
    {
        var student = await _context.Students.FindAsync(id);

        if (student == null)
        {
            return NotFound();
        }

        return student;
    }

    // PUT: api/Students/5
    // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
    [HttpPut("{id}")]
    public async Task<IActionResult> PutStudent(string id, Student student)
    {
        if (id != student.StudentId)
        {
            return BadRequest();
        }

        _context.Entry(student).State = EntityState.Modified;

        try
        {
            await _context.SaveChangesAsync();
        }
        catch (DbUpdateConcurrencyException)
        {
            if (!StudentExists(id))
            {
                return NotFound();
            }
            else
            {
                throw;
            }
        }

        return NoContent();
    }

    // POST: api/Students
    // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
    [HttpPost]
    public async Task<ActionResult<Student>> PostStudent(Student student)
    {
        _context.Students.Add(student);
        try
        {
            await _context.SaveChangesAsync();
        }
        catch (DbUpdateException)
        {
            if (StudentExists(student.StudentId))
            {
                return Conflict();
            }
            else
            {
                throw;
            }
        }

        return CreatedAtAction("GetStudent", new { id = student.StudentId }, student);
    }

    // DELETE: api/Students/5
    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteStudent(string id)
    {
        var student = await _context.Students.FindAsync(id);
        if (student == null)
        {
            return NotFound();
        }

        _context.Students.Remove(student);
        await _context.SaveChangesAsync();

        return NoContent();
    }

    private bool StudentExists(string id)
    {
        return _context.Students.Any(e => e.StudentId == id);
    }

    [HttpGet]
    [Route("schools")]
    public ActionResult GetSchools()
    {
        var db = _context;
        var data = db.Students
        .GroupBy(e => e.School)
        .Select(g => new
        {
            Name = g.Key,
            Count = g.Count()
        })
        .OrderByDescending(cp => cp.Count)
        .ToList();

        //let's instantiate the DataTable.
        var dt = new Google.DataTable.Net.Wrapper.DataTable();
        dt.AddColumn(new Column(ColumnType.String, "Name", "Name"));
        dt.AddColumn(new Column(ColumnType.Number, "Count", "Count"));

        foreach (var item in data)
        {
            Row r = dt.NewRow();
            r.AddCellRange(new Cell[] {
              new Cell(item.Name),
              new Cell(item.Count)
            });
            dt.AddRow(r);
        }

        //Let's create a Json string as expected by the Google Charts API.
        return Content(dt.GetJson());
    }
}
