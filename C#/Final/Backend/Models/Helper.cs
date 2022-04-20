using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using CsvHelper;
using CsvHelper.Configuration;
using Microsoft.Extensions.Configuration;

namespace AzureFunc.Models;

public class Helper
{
    public static IEnumerable<Player> GetPlayers()
    {
        string[] p = { Directory.GetCurrentDirectory(), Helper.GetSetting("CSV_FILENAME") };
        var csvFilePath = Path.Combine(p);

        var config = new CsvConfiguration(CultureInfo.InvariantCulture)
        {
            PrepareHeaderForMatch = args => args.Header.ToLower(),
        };

        var data = new List<Player>().AsEnumerable();
        using (var reader = new StreamReader(csvFilePath))
        {
            using (var csvReader = new CsvReader(reader, config))
            {
                data = (csvReader.GetRecords<Player>()).ToList();
            }
        }

        return data;
    }

    public static string GetSetting(string name)
    {
        IConfiguration Configuration = new ConfigurationBuilder()
            .AddJsonFile("local.settings.json", optional: true, reloadOnChange: true)
            .AddEnvironmentVariables()
            .Build();

        string connStr = Configuration[$"Values:{name}"];

        if (string.IsNullOrEmpty(connStr))
            connStr = Environment.GetEnvironmentVariable(name);

        return connStr;
    }

}