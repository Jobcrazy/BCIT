using Google.DataTable.Net.Wrapper;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using MyChart.NW;

namespace MyChart.Pages;

public class ChartDataModel : PageModel {
    private readonly ILogger<ChartDataModel> _logger;

    public ChartDataModel(ILogger<ChartDataModel> logger) {
        _logger = logger;
    }

    public ActionResult OnGet() {
        var db = new NorthwindContext();
        var data = db.Employees
        .GroupBy(e => e.Country)
        .Select(g=>new{
            Name = g.Key,
            Count = g.Count()
        })
        .OrderByDescending(cp => cp.Count)
        .ToList();

        //let's instantiate the DataTable.
        var dt = new Google.DataTable.Net.Wrapper.DataTable();
        dt.AddColumn(new Column(ColumnType.String, "Name", "Name"));
        dt.AddColumn(new Column(ColumnType.Number, "Count", "Count"));

        foreach (var item in data) {
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