using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace Code1st_.Models
{
    public class Player
    {
        public int PlayerId { get; set; }

        [Display(Name = "First Name")]
        public string? FirstName { get; set; }

        [Display(Name = "Last Name")]
        public string? LastName { get; set; }
        public string? Position { get; set; }

        public string? TeamName { get; set; }

        [ForeignKey("TeamName")]
        public Team? Team { get; set; }
    }
}