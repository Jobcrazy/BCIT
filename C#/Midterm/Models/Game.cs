using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Midterm.Models
{
    public class Game
    {
        [Key]
        public int Id { get; set; }

        [MaxLength(30)]
        public String? City { get; set; }

        [MaxLength(30)]
        public String? Country { get; set; }

        [MaxLength(30)]
        public String? Continent { get; set; }

        [MaxLength(30)]
        public String? Season { get; set; }

        public short Year { get; set; }

        public DateTime Opening { get; set; }

        public DateTime Closing { get; set; }
    }
}