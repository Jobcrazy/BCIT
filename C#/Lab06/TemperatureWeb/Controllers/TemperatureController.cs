using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Cors;

namespace TemperatureWeb.Controllers;
using Temperature;

[ApiController]
[Route("api")]
[EnableCors("AllowCORS")]
public class TemperatureController : ControllerBase
{
    private static readonly string[] Summaries = new[]
    {
        "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
    };

    private readonly ILogger<TemperatureController> _logger;

    public TemperatureController(ILogger<TemperatureController> logger)
    {
        _logger = logger;
    }

    [HttpGet]
    [Route("convert")]
    public ConverionResult Get(int mode, double src)
    {
        Conversion conv = new Conversion();
        return new ConverionResult
        {
            Code = 0,
            Message = "Success",
            Data = conv.Convert((Conversion.ConversionMode)mode, src),
        };
    }
}
