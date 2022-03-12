using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Code1st.Data.Migrations
{
    public partial class M1 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Teams",
                columns: table => new
                {
                    TeamName = table.Column<string>(type: "TEXT", nullable: false),
                    City = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Teams", x => x.TeamName);
                });

            migrationBuilder.CreateTable(
                name: "Players",
                columns: table => new
                {
                    PlayerId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    FirstName = table.Column<string>(type: "TEXT", nullable: true),
                    LastName = table.Column<string>(type: "TEXT", nullable: true),
                    Position = table.Column<string>(type: "TEXT", nullable: true),
                    TeamName = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Players", x => x.PlayerId);
                    table.ForeignKey(
                        name: "FK_Players_Teams_TeamName",
                        column: x => x.TeamName,
                        principalTable: "Teams",
                        principalColumn: "TeamName");
                });

            migrationBuilder.InsertData(
                table: "Teams",
                columns: new[] { "TeamName", "City" },
                values: new object[] { "Celtics", "Boston" });

            migrationBuilder.InsertData(
                table: "Teams",
                columns: new[] { "TeamName", "City" },
                values: new object[] { "Golden State Warriors", "Oakland" });

            migrationBuilder.InsertData(
                table: "Teams",
                columns: new[] { "TeamName", "City" },
                values: new object[] { "Lakers", "Los Angeles" });

            migrationBuilder.InsertData(
                table: "Teams",
                columns: new[] { "TeamName", "City" },
                values: new object[] { "Pelicans", "New Orleans" });

            migrationBuilder.InsertData(
                table: "Teams",
                columns: new[] { "TeamName", "City" },
                values: new object[] { "Raptors", "Toronto" });

            migrationBuilder.InsertData(
                table: "Teams",
                columns: new[] { "TeamName", "City" },
                values: new object[] { "Rockets", "Houston" });

            migrationBuilder.InsertData(
                table: "Teams",
                columns: new[] { "TeamName", "City" },
                values: new object[] { "Thunder", "Oklahoma City" });

            migrationBuilder.InsertData(
                table: "Players",
                columns: new[] { "PlayerId", "FirstName", "LastName", "Position", "TeamName" },
                values: new object[] { 1, "LeBron", "James", "Shooting Guard", "Lakers" });

            migrationBuilder.InsertData(
                table: "Players",
                columns: new[] { "PlayerId", "FirstName", "LastName", "Position", "TeamName" },
                values: new object[] { 2, "Kevin", "Durant", "Power Forward", "Golden State Warriors" });

            migrationBuilder.InsertData(
                table: "Players",
                columns: new[] { "PlayerId", "FirstName", "LastName", "Position", "TeamName" },
                values: new object[] { 3, "Stephen", "Curry", "Point Guard", "Golden State Warriors" });

            migrationBuilder.InsertData(
                table: "Players",
                columns: new[] { "PlayerId", "FirstName", "LastName", "Position", "TeamName" },
                values: new object[] { 4, "James", "Harden", "Shooting Guard", "Rockets" });

            migrationBuilder.CreateIndex(
                name: "IX_Players_TeamName",
                table: "Players",
                column: "TeamName");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Players");

            migrationBuilder.DropTable(
                name: "Teams");
        }
    }
}
