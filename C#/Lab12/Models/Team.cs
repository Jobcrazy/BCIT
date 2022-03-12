using System.ComponentModel.DataAnnotations;

namespace Code1st_.Models
{
    public class Team
    {
        [Key]
        [Display(Name = "Team Name")]
        public string? TeamName { get; set; }

        [Display(Name = "City")]
        public string? City { get; set; }

        public List<Player> Players { get; set; }
    }
}