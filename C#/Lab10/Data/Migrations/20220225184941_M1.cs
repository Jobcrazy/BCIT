using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ServerBlazorEF.Data.Migrations
{
    public partial class M1 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterDatabase()
                .Annotation("MySql:CharSet", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "Students",
                columns: table => new
                {
                    StudentId = table.Column<string>(type: "varchar(255)", nullable: false)
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    FirstName = table.Column<string>(type: "longtext", nullable: false)
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    LastName = table.Column<string>(type: "longtext", nullable: false)
                        .Annotation("MySql:CharSet", "utf8mb4"),
                    School = table.Column<string>(type: "longtext", nullable: false)
                        .Annotation("MySql:CharSet", "utf8mb4")
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Students", x => x.StudentId);
                })
                .Annotation("MySql:CharSet", "utf8mb4");

            migrationBuilder.InsertData(
                table: "Students",
                columns: new[] { "StudentId", "FirstName", "LastName", "School" },
                values: new object[,]
                {
                    { "5ca6375b-34e4-473b-a723-2200ef93c045", "Jane", "Smith", "Medicine" },
                    { "c64f7337-9c20-4bf6-b329-466c99cba8e5", "Pamela", "Baker", "Food Science" },
                    { "da5c235d-bccd-4964-af71-99692c690054", "John", "Fisher", "Engineering" },
                    { "fe62454b-91ec-44b7-9f9f-af40eb321bbb", "Peter", "Taylor", "Mining" }
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Students");
        }
    }
}
