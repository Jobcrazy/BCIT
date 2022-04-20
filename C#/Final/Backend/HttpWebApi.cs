using System;
using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Extensions.Http;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using System.Linq;
using AzureFunc.Data;
using AzureFunc.Models;

namespace Snoopy.Function
{
    public class HttpWebApi
    {
        private readonly NhlContext _context;

        public HttpWebApi(NhlContext context)
        {
            _context = context;
        }

        [FunctionName("GetPlayers")]
        public IActionResult GetPlayers(
            [HttpTrigger(AuthorizationLevel.Function, "get", Route = "players")] HttpRequest req,
            ILogger log)
        {
            log.LogInformation("C# HTTP GET/posts trigger function processed a request in GetPlayers().");

            var players = _context.Players.ToArray();

            return new OkObjectResult(players);
        }

        [FunctionName("GetPlayer")]
        public IActionResult GetUser(
            [HttpTrigger(AuthorizationLevel.Anonymous, "get", Route = "players/{id}")] HttpRequest req,
            ILogger log, int id)
        {
            log.LogInformation("C# HTTP GET/posts trigger function processed a request.");

            var player = _context.Players.FindAsync(id).Result;
            if (player == null)
            {
                return new NotFoundResult();
            }
            log.LogInformation(player.PlayerId.ToString());
            return new OkObjectResult(player);
        }

        [FunctionName("GetPlayerByCountry")]
        public IActionResult GetPlayerByCountry(
            [HttpTrigger(AuthorizationLevel.Anonymous, "get", Route = "players/country/{from}")] HttpRequest req,
            ILogger log, string from)
        {
            log.LogInformation("C# HTTP GET/posts trigger function processed a request.");

            var players = _context.Players.Where(p => p.From == from).ToArray();
            if (players == null)
            {
                return new NotFoundResult();
            }
            return new OkObjectResult(players);
        }

        [FunctionName("CreatePlayer")]
        public async Task<IActionResult> CreateUser(
                [HttpTrigger(AuthorizationLevel.Anonymous, "post", Route = "players")] HttpRequest req,
                ILogger log)
        {
            log.LogInformation("C# HTTP POST/posts trigger function processed a request.");
            string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
            var input = JsonConvert.DeserializeObject<Player>(requestBody);
            var player = new Player()
            {
                PlayerId = input.PlayerId,
                Rank = input.Rank,
                From = input.From,
                LastName = input.LastName,
                FirstName = input.FirstName,
                Team = input.Team,
                Age = input.Age,
                Position = input.Position,
            };
            _context.Add(player);
            await _context.SaveChangesAsync();
            log.LogInformation(requestBody);
            return new OkObjectResult(player);
        }

        [FunctionName("UpdatePlayer")]
        public async Task<IActionResult> UpdatePlayer(
        [HttpTrigger(AuthorizationLevel.Anonymous, "put", Route = "players/{id}")] HttpRequest req,
        ILogger log, int id)
        {
            log.LogInformation("C# HTTP PUT/posts trigger function processed a request.");
            var player = await _context.Players.FindAsync(id);
            if (player == null)
            {
                return new NotFoundResult();
            }
            string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
            var input = JsonConvert.DeserializeObject<Player>(requestBody);

            player.PlayerId = input.PlayerId;
            player.Rank = input.Rank;
            player.From = input.From;
            player.LastName = input.LastName;
            player.FirstName = input.FirstName;
            player.Team = input.Team;
            player.Age = input.Age;
            player.Position = input.Position;

            _context.Update(player);
            await _context.SaveChangesAsync();
            log.LogInformation(requestBody);
            return new OkObjectResult(player);
        }

        [FunctionName("DeletePlayer")]
        public IActionResult DeletePlayer(
        [HttpTrigger(AuthorizationLevel.Anonymous, "delete", Route = "players/{id}")] HttpRequest req,
        ILogger log, int id)
        {
            log.LogInformation("C# HTTP DELETE/posts trigger function processed a request.");
            var player = _context.Players.FindAsync(id).Result;
            if (player == null)
            {
                return new NotFoundResult();
            }
            _context.Remove(player);
            _context.SaveChangesAsync();
            return new OkResult();
        }
    }
}
