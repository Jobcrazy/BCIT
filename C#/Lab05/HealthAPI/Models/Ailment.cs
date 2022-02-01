using System.ComponentModel.DataAnnotations;

namespace HealthAPI.Models
{
    public class Ailment {
        [Key]
        [MaxLength(40)]
        public string Name { get; set; }
    }

}