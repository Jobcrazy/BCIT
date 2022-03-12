using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using Code1st.Models;
using Microsoft.Extensions.Localization;

namespace Code1st.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;
    private readonly IStringLocalizer<SharedResource> _sharedLocalizer;

    public HomeController(ILogger<HomeController> logger,
    IStringLocalizer<SharedResource> sharedLocalizer)
    {
        _logger = logger;
        _sharedLocalizer = sharedLocalizer;
    }

    public IActionResult Index()
    {
        ViewData["Welcome"] = _sharedLocalizer["Welcome"];
        ViewData["Learn about"] = _sharedLocalizer["Learn about"];
        ViewData["building Web apps with ASP.NET Core"] = _sharedLocalizer["building Web apps with ASP.NET Core"];
        return View();
    }

    public IActionResult Privacy()
    {
        ViewData["Policy"] = _sharedLocalizer["Privacy Policy"];
        ViewData["Detail"] = _sharedLocalizer["Use this page to detail your site's privacy policy."];
        return View();
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}
