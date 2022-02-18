using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Cors;
using Midterm.Models;
using Midterm.Data;
using Google.DataTable.Net.Wrapper;
using Microsoft.EntityFrameworkCore;

namespace Midterm.Controllers;

[ApiController]
[Route("api/[controller]")]
[EnableCors("AllowCORS")]
public class GamesController : ControllerBase
{
    private readonly OlympicsContext _context;

    public GamesController(OlympicsContext context)
    {
        _context = context;
    }

    [HttpGet]
    public IEnumerable<Game> Get()
    {
        return _context.Game!.Select(_ => _);
    }

    [HttpGet]
    [Route("summer")]
    public IEnumerable<Game> GetSummer()
    {
        return _context.Game!.Where(g => g.Season == "Summer");
    }

    [HttpGet]
    [Route("winter")]
    public IEnumerable<Game> GetWinter()
    {
        return _context.Game!.Where(g => g.Season == "Winter");
    }

    [HttpGet]
    [Route("summary")]
    public DataTable GetSummary()
    {
        var data = _context.Game!
        .GroupBy(e => e.Continent)
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

        return dt;
    }

    [HttpPost]
    public async Task<ActionResult<Game>> PostPatient(Game game)
    {
        _context.Game!.Add(game);
        await _context.SaveChangesAsync();

        return CreatedAtAction("GetPatient", new { id = game.Id }, game);
    }

    [HttpGet("{id:int}")]
    public async Task<ActionResult<Game>> GetGameById(int id)
    {
        var game = await _context.Game!
            .FirstOrDefaultAsync(i => i.Id == id);

        if (game == null)
            return NotFound();

        return Ok(game);
    }


    private bool GameExists(int id)
    {
        return _context.Game!.Any(e => e.Id == id);
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> PutPatient(int id, Game game)
    {
        if (id != game.Id)
        {
            return BadRequest();
        }

        _context.Entry(game).State = EntityState.Modified;

        try
        {
            await _context.SaveChangesAsync();
        }
        catch (DbUpdateConcurrencyException)
        {
            if (!GameExists(id))
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

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteGame(int id)
    {
        var game = await _context.Game!.FindAsync(id);
        if (game == null)
        {
            return NotFound();
        }

        _context.Game.Remove(game);
        await _context.SaveChangesAsync();

        return NoContent();
    }
}
