using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace OlympicsAPI.Data.Migrations
{
    public partial class M1 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Game",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    City = table.Column<string>(type: "TEXT", maxLength: 30, nullable: true),
                    Country = table.Column<string>(type: "TEXT", maxLength: 30, nullable: true),
                    Continent = table.Column<string>(type: "TEXT", maxLength: 30, nullable: true),
                    Season = table.Column<string>(type: "TEXT", maxLength: 30, nullable: true),
                    Year = table.Column<short>(type: "INTEGER", nullable: false),
                    Opening = table.Column<DateTime>(type: "TEXT", nullable: false),
                    Closing = table.Column<DateTime>(type: "TEXT", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Game", x => x.Id);
                });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 1, "Athens", new DateTime(1896, 4, 15, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Greece", new DateTime(1896, 4, 6, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1896 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 2, "Paris", new DateTime(2000, 10, 28, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "France", new DateTime(2000, 5, 14, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1900 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 3, "St. Louis", new DateTime(2004, 11, 23, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "United States", new DateTime(2004, 7, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1904 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 4, "London", new DateTime(2008, 10, 31, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "United Kingdom", new DateTime(2008, 4, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1908 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 5, "Stockholm", new DateTime(2012, 7, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Sweden", new DateTime(2012, 7, 6, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1912 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 6, "Antwerp", new DateTime(2020, 9, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Belgium", new DateTime(2020, 8, 14, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1920 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 7, "Chamonix", new DateTime(2024, 2, 5, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "France", new DateTime(2024, 1, 25, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1924 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 8, "Paris", new DateTime(2024, 7, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "France", new DateTime(2024, 7, 5, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1924 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 9, "St. Moritz", new DateTime(2028, 2, 19, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Switzerland", new DateTime(2028, 2, 11, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1928 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 10, "Amsterdam", new DateTime(2028, 8, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Netherlands", new DateTime(2028, 7, 28, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1928 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 11, "Lake Placid", new DateTime(1932, 2, 15, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "United States", new DateTime(1932, 2, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1932 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 12, "Los Angeles", new DateTime(1932, 8, 14, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "United States", new DateTime(1932, 7, 30, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1932 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 13, "Garmisch-Partenkirchen", new DateTime(1936, 2, 16, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Germany", new DateTime(1936, 2, 6, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1936 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 14, "Berlin", new DateTime(1936, 8, 16, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Germany", new DateTime(1936, 8, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1936 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 15, "St. Moritz", new DateTime(1948, 2, 8, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Switzerland", new DateTime(1948, 1, 30, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1948 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 16, "London", new DateTime(1948, 8, 14, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "United Kingdom", new DateTime(1948, 7, 29, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1948 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 17, "Oslo", new DateTime(1952, 2, 25, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Norway", new DateTime(1952, 2, 14, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1952 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 18, "Helsinki", new DateTime(1952, 8, 3, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Finland", new DateTime(1952, 7, 19, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1952 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 19, "Cortina d'Ampezzo", new DateTime(1956, 2, 5, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Italy", new DateTime(1956, 1, 26, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1956 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 20, "Melbourne", new DateTime(1956, 12, 8, 0, 0, 0, 0, DateTimeKind.Unspecified), "Oceania", "Australia", new DateTime(1956, 11, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1956 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 21, "Stockholm", new DateTime(1956, 6, 17, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Sweden", new DateTime(1956, 6, 10, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1956 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 22, "Palisades Tahoe", new DateTime(1960, 2, 28, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "United States", new DateTime(1960, 2, 18, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1960 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 23, "Rome", new DateTime(1960, 9, 11, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Italy", new DateTime(1960, 8, 25, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1960 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 24, "Innsbruck", new DateTime(1964, 2, 9, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Austria", new DateTime(1964, 1, 29, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1964 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 25, "Tokyo", new DateTime(1964, 10, 24, 0, 0, 0, 0, DateTimeKind.Unspecified), "Asia", "Japan", new DateTime(1964, 10, 10, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1964 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 26, "Grenoble", new DateTime(1968, 2, 18, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "France", new DateTime(1968, 2, 6, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1968 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 27, "Mexico City", new DateTime(1968, 10, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "Mexico", new DateTime(1968, 10, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1968 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 28, "Sapporo", new DateTime(1972, 2, 13, 0, 0, 0, 0, DateTimeKind.Unspecified), "Asia", "Japan", new DateTime(1972, 2, 3, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1972 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 29, "Munich", new DateTime(1972, 9, 11, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "West Germany", new DateTime(1972, 8, 26, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1972 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 30, "Innsbruck", new DateTime(1976, 2, 15, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Austria", new DateTime(1976, 2, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1976 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 31, "Montreal", new DateTime(1976, 8, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "Canada", new DateTime(1976, 7, 17, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1976 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 32, "Lake Placid", new DateTime(1980, 2, 24, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "United States", new DateTime(1980, 2, 13, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1980 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 33, "Moscow", new DateTime(1980, 8, 3, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Soviet Union", new DateTime(1980, 7, 19, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1980 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 34, "Sarajevo", new DateTime(1984, 2, 19, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Yugoslavia", new DateTime(1984, 2, 7, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1984 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 35, "Los Angeles", new DateTime(1984, 8, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "United States", new DateTime(1984, 7, 28, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1984 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 36, "Calgary", new DateTime(1988, 2, 28, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "Canada", new DateTime(1988, 2, 13, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1988 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 37, "Seoul", new DateTime(1988, 10, 2, 0, 0, 0, 0, DateTimeKind.Unspecified), "Asia", "South Korea", new DateTime(1988, 9, 17, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1988 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 38, "Albertville", new DateTime(1992, 2, 23, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "France", new DateTime(1992, 2, 8, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1992 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 39, "Barcelona", new DateTime(1992, 8, 9, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Spain", new DateTime(1992, 7, 25, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1992 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 40, "Lillehammer", new DateTime(1994, 2, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Norway", new DateTime(1994, 2, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1994 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 41, "Atlanta", new DateTime(1996, 8, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "United States", new DateTime(1996, 7, 19, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)1996 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 42, "Nagano", new DateTime(1998, 2, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), "Asia", "Japan", new DateTime(1998, 2, 7, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)1998 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 43, "Sydney", new DateTime(2000, 10, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), "Oceania", "Australia", new DateTime(2000, 9, 15, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)2000 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 44, "Salt Lake City", new DateTime(2002, 2, 24, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "United States", new DateTime(2002, 2, 8, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)2002 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 45, "Athens", new DateTime(2004, 8, 29, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Greece", new DateTime(2004, 8, 13, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)2004 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 46, "Turin", new DateTime(2006, 2, 26, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Italy", new DateTime(2006, 2, 10, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)2006 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 47, "Beijing", new DateTime(2008, 8, 24, 0, 0, 0, 0, DateTimeKind.Unspecified), "Asia", "China", new DateTime(2008, 8, 8, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)2008 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 48, "Vancouver", new DateTime(2010, 2, 28, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "Canada", new DateTime(2010, 2, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)2010 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 49, "London", new DateTime(2012, 8, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "United Kingdom", new DateTime(2012, 7, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)2012 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 50, "Sochi", new DateTime(2014, 2, 23, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Russia", new DateTime(2014, 2, 7, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)2014 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 51, "Rio de Janeiro", new DateTime(2016, 8, 21, 0, 0, 0, 0, DateTimeKind.Unspecified), "South America", "Brazil", new DateTime(2016, 8, 5, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)2016 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 52, "Pyeongchang", new DateTime(2018, 2, 25, 0, 0, 0, 0, DateTimeKind.Unspecified), "Asia", "South Korea", new DateTime(2018, 2, 9, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)2018 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 53, "Tokyo", new DateTime(2021, 8, 8, 0, 0, 0, 0, DateTimeKind.Unspecified), "Asia", "Japan", new DateTime(2021, 7, 23, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)2020 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 54, "Beijing", new DateTime(2022, 2, 20, 0, 0, 0, 0, DateTimeKind.Unspecified), "Asia", "China", new DateTime(2022, 2, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)2022 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 55, "Paris", new DateTime(2024, 8, 11, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "France", new DateTime(2024, 7, 26, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)2024 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 56, "Milan–Cortina d'Ampezzo", new DateTime(2026, 2, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), "Europe", "Italy", new DateTime(2026, 2, 6, 0, 0, 0, 0, DateTimeKind.Unspecified), "Winter", (short)2026 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 57, "Los Angeles", new DateTime(2028, 8, 6, 0, 0, 0, 0, DateTimeKind.Unspecified), "North America", "United States", new DateTime(2028, 7, 21, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)2028 });

            migrationBuilder.InsertData(
                table: "Game",
                columns: new[] { "Id", "City", "Closing", "Continent", "Country", "Opening", "Season", "Year" },
                values: new object[] { 58, "Brisbane", new DateTime(1932, 8, 8, 0, 0, 0, 0, DateTimeKind.Unspecified), "Oceania", "Australia", new DateTime(1932, 7, 23, 0, 0, 0, 0, DateTimeKind.Unspecified), "Summer", (short)2032 });

            migrationBuilder.CreateIndex(
                name: "IX_Game_Id",
                table: "Game",
                column: "Id",
                unique: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Game");
        }
    }
}
