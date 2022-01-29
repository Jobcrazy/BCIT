using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace CityLab.Models
{
    public class City
    {
        public int CityId { get; set; }

        [DisplayName("City")]
        public String? CityName { get; set; }

        public int? Population { get; set; }

        public string? ProvinceCode { get; set; }

        [ForeignKey("ProvinceCode")]
        public Province? Province { get; set; }
    }
}