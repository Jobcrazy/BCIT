using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CityLab.Models;

namespace CityLab.Data
{
    public class SampleData
    {
        public static List<Province> GetProvinces()
        {
            List<Province> provinces = new List<Province>() {
                new Province() {
                    ProvinceCode="BC",
                    ProvinceName="British Columbia"
                },
                new Province() {
                    ProvinceCode="ON",
                    ProvinceName="Ontario"
                },
                new Province() {
                    ProvinceCode="QC",
                    ProvinceName="Quebec"
                },
            };

            return provinces;
        }

        public static List<City> GetCities()
        {
            List<City> cities = new List<City>() {
                new City {
                    CityId=1,
                    CityName="Vancouver",
                    Population=100,
                    ProvinceCode="BC"
                },
                new City {
                    CityId=2,
                    CityName="Richmond",
                    Population=200,
                    ProvinceCode="BC"
                },
                new City {
                    CityId=3,
                    CityName="Burnaby",
                    Population=300,
                    ProvinceCode="BC"
                },
                new City {
                    CityId=4,
                    CityName="Toronto",
                    Population=400,
                    ProvinceCode="ON"
                },
                new City {
                    CityId=5,
                    CityName="Ottawa",
                    ProvinceCode="ON",
                    Population=500,
                },
                new City {
                    CityId=6,
                    CityName="Hamilton",
                    ProvinceCode="ON",
                    Population=600,
                },
                new City {
                    CityId=7,
                    CityName="Montreal",
                    Population=700,
                    ProvinceCode="QC"
                },
                new City {
                    CityId=8,
                    CityName="Quebec City",
                    Population=800,
                    ProvinceCode="QC"
                },
                new City {
                    CityId=9,
                    CityName="Sherbrooke",
                    Population=900,
                    ProvinceCode="QC"
                },
            };
            return cities;
        }
    }
}