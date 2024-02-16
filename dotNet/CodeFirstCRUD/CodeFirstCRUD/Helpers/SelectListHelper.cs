using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace CodeFirstCRUD.Helpers
{
    public static class SelectListHelper
    {
        public static List<SelectListItem> GetCountryList()
        {
            List<SelectListItem> countries = new List<SelectListItem>
        {
            new SelectListItem { Text = "India", Value = "India" }, // Default option
            new SelectListItem { Text = "United States", Value = "United States" },
            new SelectListItem { Text = "Canada", Value = "Canada" },
            new SelectListItem { Text = "United Kingdom", Value = "United Kingdom" },
            new SelectListItem { Text = "Germany", Value = "Germany" },
            new SelectListItem { Text = "Australia", Value = "Australia" },
            new SelectListItem { Text = "France", Value = "France" },
            new SelectListItem { Text = "Italy", Value = "Italy" },
            new SelectListItem { Text = "Spain", Value = "Spain" },
            new SelectListItem { Text = "Japan", Value = "Japan" },
            new SelectListItem { Text = "Brazil", Value = "Brazil" },
            new SelectListItem { Text = "Mexico", Value = "Mexico" },
            new SelectListItem { Text = "Russia", Value = "Russia" },
            new SelectListItem { Text = "China", Value = "China" },

        };
            return countries;
        }
    }
}