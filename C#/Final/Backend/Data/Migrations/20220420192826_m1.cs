using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace FunctionsFinal.Data.Migrations
{
    public partial class m1 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterDatabase()
                .Annotation("MySql:CharSet", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "Player",
                columns: table => new
                {
                    PlayerId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn),
                    Rank = table.Column<int>(type: "int", nullable: false),
                    From = table.Column<string>(type: "longtext", nullable: true)
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    FirstName = table.Column<string>(type: "longtext", nullable: false)
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    LastName = table.Column<string>(type: "longtext", nullable: false)
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    Team = table.Column<string>(type: "longtext", nullable: true)
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    Age = table.Column<int>(type: "int", nullable: false),
                    Position = table.Column<string>(type: "longtext", nullable: true)
                        .Annotation("MySql:CharSet", "utf8mb4")
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Player", x => x.PlayerId);
                })
                .Annotation("MySql:CharSet", "utf8mb4");

            migrationBuilder.InsertData(
                table: "Player",
                columns: new[] { "PlayerId", "Age", "FirstName", "From", "LastName", "Position", "Rank", "Team" },
                values: new object[,]
                {
                    { 1, 24, "Connor", "Canada", "McDavid", "F", 1, "EDM" },
                    { 2, 26, "Leon", "Germany", "Draisaitl", "F", 2, "EDM" },
                    { 3, 28, "Jonathan", "Canada", "Huberdeau", "F", 3, "FLA" },
                    { 4, 28, "Johnny", "USA", "Gaudreau", "F", 4, "CGY" },
                    { 5, 24, "Auston", "USA", "Matthews", "F", 5, "TOR" },
                    { 6, 24, "Kirill", "Russia", "Kaprizov", "F", 6, "MIN" },
                    { 7, 24, "Matthew", "USA", "Tkachuk", "F", 7, "CGY" },
                    { 8, 31, "Nazem", "Canada", "Kadri", "F", 8, "COL" },
                    { 9, 25, "Kyle", "USA", "Connor", "F", 9, "WPG" },
                    { 10, 25, "Mikko", "Finland", "Rantanen", "F", 10, "COL" },
                    { 11, 24, "Mitchell", "Canada", "Marner", "F", 11, "TOR" },
                    { 12, 28, "J.T. ", "USA", "Miller", "F", 12, "VAN" },
                    { 13, 31, "Roman", "Switzerland", "Josi", "D", 13, "NSH" },
                    { 14, 30, "Artemi", "Russia", "Panarin", "F", 14, "NYR" },
                    { 15, 33, "Patrick", "USA", "Kane", "F", 15, "CHI" },
                    { 16, 36, "Alexander", "Russia", "Ovechkin", "F", 16, "WSH" },
                    { 17, 31, "Steven", "Canada", "Stamkos", "F", 17, "TBL" },
                    { 18, 23, "Cale", "Canada", "Makar", "D", 18, "COL" },
                    { 19, 34, "Sidney", "Canada", "Crosby", "F", 19, "PIT" },
                    { 20, 26, "Nathan", "Canada", "MacKinnon", "F", 20, "COL" },
                    { 21, 25, "David", "Czeck Republic", "Pastrnak", "F", 21, "BOS" },
                    { 22, 33, "Brad", "Canada", "Marchand", "F", 22, "BOS" },
                    { 23, 30, "Matt", "Canada", "Duchene", "F", 23, "NSH" },
                    { 24, 26, "Aleksander", "Finland", "Barkov", "F", 24, "FLA" },
                    { 25, 28, "Mika", "Sweden", "Zibanejad", "F", 25, "NYR" },
                    { 26, 27, "Elias", "Sweden", "Lindholm", "F", 26, "CGY" },
                    { 27, 24, "Sebastian", "Finland", "Aho", "F", 27, "CAR" },
                    { 28, 34, "Mats", "Norway", "Zuccarello", "F", 28, "MIN" },
                    { 29, 27, "Jake", "USA", "Guentzel", "F", 29, "PIT" },
                    { 30, 37, "Joe", "USA", "Pavelski", "F", 30, "DAL" },
                    { 31, 27, "Filip", "Sweden", "Forsberg", "F", 31, "NSH" },
                    { 32, 25, "Timo", "Switzerland", "Meier", "F", 32, "SJS" },
                    { 33, 31, "Victor", "Sweden", "Hedman", "D", 33, "TBL" },
                    { 34, 23, "Adam", "USA", "Fox", "D", 34, "NYR" },
                    { 35, 30, "Chris", "USA", "Kreider", "F", 35, "NYR" },
                    { 36, 24, "Alex", "USA", "DeBrincat", "F", 36, "CHI" },
                    { 37, 28, "Mark", "Canada", "Scheifele", "F", 37, "WPG" },
                    { 38, 26, "Sam", "Canada", "Reinhart", "F", 38, "FLA" },
                    { 39, 31, "John", "Canada", "Tavares", "F", 39, "TOR" },
                    { 40, 23, "Jesper", "Sweden", "Bratt", "F", 40, "NJD" },
                    { 41, 29, "Evgeny", "Russia", "Kuznetsov", "F", 41, "WSH" },
                    { 42, 25, "William", "Sweden", "Nylander", "F", 42, "TOR" },
                    { 43, 22, "Jason", "USA", "Robertson", "F", 43, "DAL" },
                    { 44, 25, "Dylan", "USA", "Larkin", "F", 44, "DET" },
                    { 45, 23, "Clayton", "USA", "Keller", "F", 45, "ARI" },
                    { 46, 23, "Jordan", "Canada", "Kyrou", "F", 46, "STL" },
                    { 47, 25, "Kevin", "Switzerland", "Fiala", "F", 47, "MIN" },
                    { 48, 21, "Andrei", "Russia", "Svechnikov", "F", 48, "CAR" },
                    { 49, 34, "Kristopher", "Canada", "Letang", "D", 49, "PIT" },
                    { 50, 29, "Gabriel", "Sweden", "Landeskog", "F", 50, "COL" }
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Player");
        }
    }
}
