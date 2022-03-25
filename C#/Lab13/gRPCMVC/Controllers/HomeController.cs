using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using gRPCMVC.Models;
using Grpc.Net.Client;
using GrpcServer.Protos;

namespace gRPCMVC.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;

    public HomeController(ILogger<HomeController> logger)
    {
        _logger = logger;
    }

    public IActionResult Index()
    {
        var channel = GrpcChannel.ForAddress("http://localhost:5114");
        var sClient = new StudentRemote.StudentRemoteClient(channel);
        ViewData["students"] = sClient.RetrieveAllStudents(new Empty { });
        return View();
    }

    public IActionResult Privacy()
    {
        return View();
    }

    public IActionResult Add()
    {
        return View();
    }

    [HttpPost]
    public IActionResult Add(StudentModel student)
    {
        var channel = GrpcChannel.ForAddress("http://localhost:5114");
        var sClient = new StudentRemote.StudentRemoteClient(channel);
        ViewData["student"] = sClient.InsertStudent(student);
        return RedirectToAction(nameof(Index));
    }

    public IActionResult Edit(int id)
    {
        var channel = GrpcChannel.ForAddress("http://localhost:5114");
        var sClient = new StudentRemote.StudentRemoteClient(channel);
        ViewData["student"] = sClient.GetStudentInfo(new StudentLookupModel { StudentId = id });
        return View();
    }

    [HttpPost]
    public IActionResult Edit(StudentModel student)
    {
        var channel = GrpcChannel.ForAddress("http://localhost:5114");
        var sClient = new StudentRemote.StudentRemoteClient(channel);
        ViewData["student"] = sClient.UpdateStudent(student);
        return RedirectToAction(nameof(Index));
    }

    public IActionResult Delete(int id)
    {
        var channel = GrpcChannel.ForAddress("http://localhost:5114");
        var sClient = new StudentRemote.StudentRemoteClient(channel);
        ViewData["student"] = sClient.GetStudentInfo(new StudentLookupModel { StudentId = id });
        return View();
    }

    // POST: Categories/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public IActionResult DeleteConfirmed(int id)
    {
        var channel = GrpcChannel.ForAddress("http://localhost:5114");
        var sClient = new StudentRemote.StudentRemoteClient(channel);
        var deleteReply = sClient.DeleteStudent(new StudentLookupModel { StudentId = id });
        return RedirectToAction(nameof(Index));
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}
