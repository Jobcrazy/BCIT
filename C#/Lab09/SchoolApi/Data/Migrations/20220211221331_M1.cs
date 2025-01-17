﻿using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace SchoolApi.Data.Migrations
{
    public partial class M1 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Students",
                columns: table => new
                {
                    StudentId = table.Column<string>(type: "nvarchar(450)", nullable: false),
                    FirstName = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    LastName = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    School = table.Column<string>(type: "nvarchar(max)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Students", x => x.StudentId);
                });

            migrationBuilder.InsertData(
                table: "Students",
                columns: new[] { "StudentId", "FirstName", "LastName", "School" },
                values: new object[,]
                {
                    { "6206a1eef07a45a08420fe5b", "Ann", "Fay", "Mining" },
                    { "6206a1eef07a45a08420fe5c", "Joe", "Sun", "Nursing" },
                    { "6206a1eef07a45a08420fe5d", "Ben", "Ray", "Mining" },
                    { "6206a1eef07a45a08420fe5e", "Zoe", "Cox", "Business" },
                    { "6206a1eef07a45a08420fe5f", "Sam", "Ray", "Mining" },
                    { "6206a1eef07a45a08420fe60", "Dan", "Ash", "Medicine" },
                    { "6206a1eef07a45a08420fe61", "Pat", "Lee", "Computing" },
                    { "6206a1eef07a45a08420fe62", "Kim", "Day", "Science" },
                    { "6206a1eef07a45a08420fe63", "Tim", "Rex", "Computing" },
                    { "6206a1eef07a45a08420fe64", "Rob", "Ram", "Business" },
                    { "6206a1eef07a45a08420fe65", "Jan", "Fry", "Mining" },
                    { "6206a1eef07a45a08420fe66", "Jim", "Tex", "Science" },
                    { "6206a1eef07a45a08420fe67", "Ben", "Kid", "Business" },
                    { "6206a1eef07a45a08420fe68", "Mia", "Chu", "Medicine" },
                    { "6206a1eef07a45a08420fe69", "Ted", "Tao", "Computing" },
                    { "6206a1eef07a45a08420fe6a", "Amy", "Day", "Business" },
                    { "6206a1eef07a45a08420fe6b", "Ian", "Roy", "Science" },
                    { "6206a1eef07a45a08420fe6c", "Liz", "Kit", "Nursing" },
                    { "6206a1eef07a45a08420fe6d", "Mat", "Tan", "Medicine" },
                    { "6206a1eef07a45a08420fe6e", "Deb", "Roy", "Medicine" },
                    { "6206a1eef07a45a08420fe6f", "Ana", "Ray", "Tourism" },
                    { "6206a1eef07a45a08420fe70", "Lyn", "Poe", "Computing" },
                    { "6206a1eef07a45a08420fe71", "Amy", "Raj", "Science" },
                    { "6206a1eef07a45a08420fe72", "Kim", "Ash", "Mining" },
                    { "6206a1eef07a45a08420fe73", "Bec", "Kid", "Nursing" },
                    { "6206a1eef07a45a08420fe74", "Eva", "Fry", "Computing" },
                    { "6206a1eef07a45a08420fe75", "Eli", "Lap", "Business" },
                    { "6206a1eef07a45a08420fe76", "Sam", "Yim", "Science" },
                    { "6206a1eef07a45a08420fe77", "Joe", "Hui", "Mining" },
                    { "6206a1eef07a45a08420fe78", "Liz", "Jin", "Nursing" },
                    { "6206a1eef07a45a08420fe79", "Ric", "Kuo", "Linguistics" },
                    { "6206a1eef07a45a08420fe7a", "Pam", "Mak", "Computing" },
                    { "6206a1eef07a45a08420fe7b", "Stu", "Day", "Business" },
                    { "6206a1eef07a45a08420fe7c", "Tom", "Gad", "Tourism" },
                    { "6206a1eef07a45a08420fe7d", "Bob", "Bee", "Agriculture" },
                    { "6206a1eef07a45a08420fe7e", "Jim", "Ots", "Agriculture" },
                    { "6206a1eef07a45a08420fe7f", "Tom", "Mag", "Linguistics" },
                    { "6206a1eef07a45a08420fe80", "Hal", "Doe", "Mining" },
                    { "6206a1eef07a45a08420fe81", "Roy", "Kim", "Tourism" },
                    { "6206a1eef07a45a08420fe82", "Vis", "Cox", "Nursing" },
                    { "6206a1eef07a45a08420fe83", "Kay", "Aga", "Science" },
                    { "6206a1eef07a45a08420fe84", "Reo", "Hui", "Nursing" }
                });

            migrationBuilder.InsertData(
                table: "Students",
                columns: new[] { "StudentId", "FirstName", "LastName", "School" },
                values: new object[] { "6206a1eef07a45a08420fe85", "Bob", "Roe", "Mining" });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Students");
        }
    }
}
