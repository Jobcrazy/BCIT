using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace CityLab.Models
{
    public class Province
    {
        [Key]
        [DisplayName("Province Code")]
        public String? ProvinceCode { get; set; }

        [DisplayName("Province")]
        public String? ProvinceName { get; set; }

        public List<City>? Cities { get; set; }
    }
}